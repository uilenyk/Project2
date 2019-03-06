package com.revature.repository;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.Listing;

@Repository
public class ListingRepository {

	@Autowired
	private EntityManagerFactory emf;

	public Listing findListingById(int id) {
		return null;
	}

	public List<Listing> findAllListings() {
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

	public void delete(Listing listing) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			session.delete(listing);
		}
	}

	public Listing update(Listing listing) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try(Session session = sf.openSession()){
			Transaction tx = session.beginTransaction();
			Listing current = session.get(Listing.class, listing.getListid());
			if(current == null) {
				return null;
			}
			updateListing(current, listing);
			session.merge(current);
			tx.commit();
			return current;
		}
	}
	
	private void updateListing(Listing current, Listing updated) {
		if(updated.getActive() != null) {
			current.setActive(updated.getActive());
		}
		if(updated.getDescription() != null) {
			current.setDescription(updated.getDescription());
		}
		if(updated.getImages() != null) {
			current.setImages(updated.getImages());
		}
		if(updated.getPrice() != null) {
			current.setPrice(updated.getPrice());
		}
		if(updated.getTags() != null) {
			current.setTags(updated.getTags());
		}
	}

}
