package com.revature.repository;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.MarketPlaceUser;
import com.revature.models.requests.LoginRequest;

@Repository
public class MarketPlaceUserRepository {

	@Autowired
	private EntityManagerFactory emf;

	public MarketPlaceUser create(MarketPlaceUser marketPlaceUser) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			int id = (int) session.save(marketPlaceUser);
			marketPlaceUser.setId(id);
			return marketPlaceUser;
		}
	}
	
	public MarketPlaceUser findUserByCredentials(LoginRequest loginRequest) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			Query<?> query = session.getNamedQuery("findPasswordByUserCredentials");
			query.setParameter("email", loginRequest.getEmail());
			return (MarketPlaceUser) query.getSingleResult();
		}
	}
}
