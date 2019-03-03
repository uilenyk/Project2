package com.revature.repository;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.MarketPlaceUser;

@Repository
public class MarketPlaceUserRepository {

	@Autowired
	private EntityManagerFactory emf;

	public MarketPlaceUser findBy(int id) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			MarketPlaceUser marketPlaceUser = session.find(MarketPlaceUser.class, id);
			Hibernate.initialize(marketPlaceUser.getMarketPlaceUserListings());
			return marketPlaceUser;
		}
	}

	public MarketPlaceUser create(MarketPlaceUser marketPlaceUser) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			int id = (int) session.save(marketPlaceUser);
			marketPlaceUser.setMpuid(id);
			return marketPlaceUser;
		}
	}

}
