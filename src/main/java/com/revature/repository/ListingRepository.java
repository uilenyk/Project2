package com.revature.repository;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.Listing;

@Repository
public class ListingRepository {

	@Autowired
	private EntityManagerFactory emf;

	public Listing findBy(int id) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			Listing listing = session.find(Listing.class, id);
			return listing;
		}
	}

	public List<Listing> findAll() {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			Query<?> query = session.getNamedQuery("Listing.findAll");
			List<Listing> listings = (List<Listing>) query.getResultList();
			return listings;
		}
	}

	public Listing create(Listing listing) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			int id = (int) session.save(listing);
			listing.setListid(id);
			return listing;
		}
	}

	public Listing update(Listing listing) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			session.update(listing);
			return session.find(Listing.class, listing.getListid());			
		}
	}

	public void delete(int listid) {

	}

}
