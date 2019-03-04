package com.revature.repository;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

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
			Transaction tx = session.beginTransaction();
			session.update(listing);
			Listing updatedListing = session.find(Listing.class, listing.getListid());
			tx.commit();
			return updatedListing;
		}
	}

	public void patch(ListingPatchRequest request) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			CriteriaBuilder cb = emf.getCriteriaBuilder();
			CriteriaUpdate<Listing> update = cb.createCriteriaUpdate(Listing.class);
			Root<Listing> root = update.from(Listing.class);
			update.set("name", request.getName());
			update.set("description", request.getDescription());
			update.set("price", request.getPrice());
			update.set("tags", request.getTags());
			update.where(cb.equal(root.get("listid"), request.getListid()));
			session.createQuery(update).executeUpdate();
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

}
