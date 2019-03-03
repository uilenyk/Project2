package com.revature.repository;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.Message;

@Repository
public class MessageRepository {

	@Autowired
	private EntityManagerFactory emf;

	public Message createMessage(Message newMessage, int parentId) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try(Session session = sf.openSession()){
			Transaction tx = session.beginTransaction();
			if(parentId != 0) {
				Message parent = session.get(Message.class, parentId);
				newMessage.setParent(parent);
			}
			session.persist(newMessage);
			session.flush();
			tx.commit();
			int id = newMessage.getId();
			if(id == 0) {
				return null;
			} else {
				return newMessage;
			}
		}
	}

	public List<Message> getMessages(int userId) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try(Session session = sf.openSession()){
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Message> messages = cb.createQuery(Message.class);
			Root<Message> root = messages.from(Message.class);
			
			messages.select(root)
				.where(cb.equal(root.get("receiver").<Integer>get("mpuid"), new Integer(userId)));
			
			Query<Message> query = session.createQuery(messages);
			List<Message> results = query.getResultList();
			return results;
		}
	}

}
