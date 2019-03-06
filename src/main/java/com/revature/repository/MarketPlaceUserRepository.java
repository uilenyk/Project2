package com.revature.repository;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.Listing;
import com.revature.models.MarketPlaceUser;

@Repository
public class MarketPlaceUserRepository {
	private static Logger log = LoggerFactory.getLogger(MarketPlaceUserRepository.class);

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

	public MarketPlaceUser create(MarketPlaceUser marketPlaceUser) {
		return null;
	}

	public MarketPlaceUser update(MarketPlaceUser updateUser) {
		System.out.println(updateUser.toString());
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			MarketPlaceUser user = session.get(MarketPlaceUser.class, updateUser.getMpuid());
			if (user == null) {
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
			session.merge(user);
			session.flush();
			tx.commit();
		}

	}

//	public MarketPlaceUser findBy(int id) {
//		log.debug("id in mpu repo: " + id);
//		SessionFactory sf = emf.unwrap(SessionFactory.class);
//		try (Session session = sf.openSession()) {
//			MarketPlaceUser marketPlaceUser = session.load(MarketPlaceUser.class, id);
//			Hibernate.initialize(marketPlaceUser.getMarketPlaceUserListings());
////			Hibernate.initialize(marketPlaceUser.getSentMessages());
////			Hibernate.initialize(marketPlaceUser.getReceivedMessages());
//			return marketPlaceUser;
//		}
//	}

	public MarketPlaceUser findByPsudoname(String userName) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<MarketPlaceUser> user = cb.createQuery(MarketPlaceUser.class);
			Root<MarketPlaceUser> root = user.from(MarketPlaceUser.class);

			user.select(root).where(cb.equal(root.get("pseudoname"), userName));

			Query<MarketPlaceUser> query = session.createQuery(user);
			List<MarketPlaceUser> results = query.getResultList();
			if (results != null && !results.isEmpty())
				return results.get(0);
		}
		return null;
	}

}
