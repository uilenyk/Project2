package com.revature.repository;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.Listing;
import com.revature.models.MarketPlaceUser;

@Repository
public class MarketPlaceUserRepository {

	@Autowired
	private EntityManagerFactory emf;

	public MarketPlaceUser findBy(int id) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			MarketPlaceUser marketPlaceUser = session.get(MarketPlaceUser.class, id);
			List<Listing> listings = marketPlaceUser.getListings();
			Hibernate.initialize(listings);
			return marketPlaceUser;
		}
	}

	public MarketPlaceUser create(MarketPlaceUser user) {
		return null;
	}

	public MarketPlaceUser update(MarketPlaceUser user) {
		System.out.println(user.toString());
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			MarketPlaceUser updatedUser = session.get(MarketPlaceUser.class, user.getMpuid());
			session.merge(user);
			tx.commit();
			if (updatedUser != null) {
				return updatedUser;
			} else {
				return null;
			}
		}

	}

}
