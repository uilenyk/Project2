package com.revature.repository;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Hibernate;
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

	public MarketPlaceUser update(MarketPlaceUser updateUser) {
		System.out.println(updateUser.toString());
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			MarketPlaceUser user = session.get(MarketPlaceUser.class, updateUser.getMpuid());
			if(user == null) {
				return null;
			}
			System.out.println(user.toString());
			updateUser(user, updateUser);
			System.out.println(user.toString());
			session.merge(user);
			// MarketPlaceUser u = session.get(MarketPlaceUser.class, user.getMpuid());
			tx.commit();
			if (user != null) 
				return user;
			else
				return null;
		}
	}

	private void updateUser(MarketPlaceUser old, MarketPlaceUser update) {
		if (update.getFirstname() != null)
			old.setFirstname(update.getFirstname());
		if (update.getLastname() != null)
			old.setLastname(update.getLastname());
		if (update.getPhoneNumber() != null) {
			old.getPhoneNumber().setAreaCodeThree(update.getPhoneNumber().getAreaCodeThree());
			old.getPhoneNumber().setBlockFour(update.getPhoneNumber().getBlockFour());
			old.getPhoneNumber().setBlockThree(update.getPhoneNumber().getBlockThree());
		}
		if (update.getAddress() != null) {
			old.getAddress().setStreetnumber(update.getAddress().getStreetnumber());
			old.getAddress().setStreetname(update.getAddress().getStreetname());
			old.getAddress().setCity(update.getAddress().getCity());
			old.getAddress().setState(update.getAddress().getState());
			old.getAddress().setZipcode(update.getAddress().getZipcode());
		}
	}

	public void messageAlert(MarketPlaceUser receiver) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			MarketPlaceUser user = session.get(MarketPlaceUser.class, receiver.getMpuid());
			user.setNewMessage(true);
			session.flush();
			tx.commit();
		}

	}

	public MarketPlaceUser findBy(int id) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			MarketPlaceUser marketPlaceUser = session.get(MarketPlaceUser.class, id);
			// Hibernate.initialize(marketPlaceUser.getMarketPlaceUserListings());
			return marketPlaceUser;
		}
	}

}
