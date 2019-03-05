package com.revature.repository;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.Message;

@Repository
public class MessageRepository {
	
	private Logger log = Logger.getRootLogger();

	@Autowired
	private EntityManagerFactory emf;

	/**
	 * Creates a new message and add the parent id if it is not the first message of a conversation
	 * @param newMessage: the message to be added to the database
	 * @param parentId: the id of the message the newMessage is replying to
	 * @return: returns the message on success
	 */
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

	/**
	 * Gets all the messages the has been sent to the user
	 * @param userId: mpuid or id of the user on the database
	 * @return: list of messages
	 */
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
			log.debug(results.get(0).getReceiver().getPhoneNumber().toString());
			return results;
		}
	}
	
	public List<Message> getSentMessages(int userId) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try(Session session = sf.openSession()){
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Message> messages = cb.createQuery(Message.class);
			Root<Message> root = messages.from(Message.class);
			
			messages.select(root)
				.where(cb.equal(root.get("sender").<Integer>get("mpuid"), new Integer(userId)));
			
			Query<Message> query = session.createQuery(messages);
			List<Message> results = query.getResultList();
			
			for(Message m: results) {
				Hibernate.initialize(m.getReceiver());
				Hibernate.initialize(m.getSender());
			}
			
			return results;
		}
	}

}
