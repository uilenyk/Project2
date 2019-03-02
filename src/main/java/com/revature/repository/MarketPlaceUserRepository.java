package com.revature.repository;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.MarketPlaceUser;

@Repository
public class MarketPlaceUserRepository {

	@Autowired
	private EntityManagerFactory emf;

	public MarketPlaceUser create(MarketPlaceUser marketPlaceUser) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			int id = (int) session.save(marketPlaceUser);
			marketPlaceUser.setMpuid(id);
			return marketPlaceUser;
		}
	}

	public MarketPlaceUser update(MarketPlaceUser user) {
		System.out.println(user.toString());
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try(Session session = sf.openSession()){
			Transaction tx = session.beginTransaction();
			MarketPlaceUser updatedUser = session.get(MarketPlaceUser.class, user.getMpuid());
			session.merge(user);
			//MarketPlaceUser u = session.get(MarketPlaceUser.class,  user.getMpuid());
			tx.commit();
			if(updatedUser != null) {
				return updatedUser;
			} else {
				return null;
			}
		}
		
	}
	
//	public MarketPlaceUser findUserByCredentials(LoginRequest loginRequest) {
//		SessionFactory sf = emf.unwrap(SessionFactory.class);
//		try (Session session = sf.openSession()) {
//			Query<?> query = session.getNamedQuery("findPasswordByUserCredentials");
//			query.setParameter("email", loginRequest.getEmail());
//			return (MarketPlaceUser) query.getSingleResult();
//		}
//	}
}
