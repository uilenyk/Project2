package com.revature.repository;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.Listing;
import com.revature.models.requests.ListingPatchRequest;
import com.revature.models.requests.MakeInactiveRequest;

@Repository
public class ListingRepository {

	@Autowired
	private EntityManagerFactory emf;

	public Listing findBy(int id) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			Listing listing = session.find(Listing.class, id);
			Hibernate.initialize(listing.getOwner());
			Hibernate.initialize(listing.getTags());
			return listing;
		}
	}

	public List<Listing> findAll() {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			Query<?> query = session.getNamedQuery("Listing.findAll");
			@SuppressWarnings("unchecked")
			List<Listing> listings = (List<Listing>) query.getResultList();
			for (int i = 0; i < listings.size(); i++) {
				Listing listing = listings.get(i);
				Hibernate.initialize(listing.getOwner());
				Hibernate.initialize(listing.getTags());
			}
			return listings;
		}
	}

	public Listing create(Listing listing) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			int id = (int) session.save(listing);
			Listing createdListing = session.get(Listing.class, id);
			tx.commit();
			return createdListing;
		}
	}

	public void patch(ListingPatchRequest request) {
		System.out.println(request);
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			Listing existingListing = session.get(Listing.class, request.getListid());
			Hibernate.initialize(existingListing.getTags());
			existingListing.setDescription(request.getDescription());
			existingListing.setName(request.getName());
			existingListing.setPrice(request.getPrice());
			existingListing.getTags().addAll(request.getTags());
			session.merge(existingListing);
			tx.commit();
		}
	}

	public void patch(MakeInactiveRequest request) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();			
			CriteriaBuilder cb = emf.getCriteriaBuilder();
			CriteriaUpdate<Listing> update = cb.createCriteriaUpdate(Listing.class);
			Root<Listing> root = update.from(Listing.class);
			update.set("active", request.getActive());
			update.where(cb.equal(root.get("listid"), request.getListid()));
			session.createQuery(update).executeUpdate();
			tx.commit();
		}

	}

	public void delete(int listid) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			Listing listing = session.find(Listing.class, listid);
			session.delete(listing);
			tx.commit();
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
	
//	public Listing update(Listing listing) {
//		SessionFactory sf = emf.unwrap(SessionFactory.class);
//		try (Session session = sf.openSession()) {
//			Transaction tx = session.beginTransaction();
//			session.update(listing);
//			Listing updatedListing = session.find(Listing.class, listing.getListid());
//			tx.commit();
//			return updatedListing;
//		}
//	}
	
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
