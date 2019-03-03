package com.revature.repository;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.Message;

@Repository
public class MessageRepository {

	@Autowired
	private EntityManagerFactory emf;

	public Message createMessage(Message newMessage) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try(Session session = sf.openSession()){
			Transaction tx = session.beginTransaction();
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
}
