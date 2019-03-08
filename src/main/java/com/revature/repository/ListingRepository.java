package com.revature.repository;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.CreditCard;
import com.revature.models.Images;
import com.revature.models.Listing;
import com.revature.models.MarketPlaceUser;
import com.revature.models.requests.ListingPatchRequest;
import com.revature.models.requests.MakeInactiveRequest;
import com.revature.models.response.BuyerReceipt;

@Repository
public class ListingRepository {
	private Logger log = Logger.getRootLogger();

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
			List<Images> images = null;
			if (listing.getImages() != null) {
				images = listing.getImages();
				listing.setImages(null);
			}
			int id = (int) session.save(listing);
			if (images != null && id != 0) {
				for (Images i : images) {
					session.save(i);
				}
				listing.setImages(images);
			}
			//Listing createdListing = session.get(Listing.class, id);
			session.flush();
			tx.commit();
			log.debug(listing);
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
		System.out.println(request);
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			Listing existingListing = session.get(Listing.class, request.getListid());
			Hibernate.initialize(existingListing.getTags());
			updateInfo(existingListing, request);
			session.merge(existingListing);
			tx.commit();
		}
	}

	private void updateInfo(Listing current, ListingPatchRequest updated) {
		if (updated.getDescription() != null)
			current.setDescription(updated.getDescription());
		if (updated.getName() != null)
			current.setName(updated.getName());
		if (updated.getPrice() != null)
			current.setPrice(updated.getPrice());
		if (updated.getTags() != null)
			current.getTags().addAll(updated.getTags());
		if (updated.getImages() != null)
			current.setImages(updated.getImages());
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

	public BuyerReceipt buyListing(Listing listing, MarketPlaceUser buyer, MarketPlaceUser seller) {
		BuyerReceipt result = new BuyerReceipt();
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try(Session session = sf.openSession()){
			Transaction tx = session.beginTransaction();
			log.debug(listing.getListid());
			boolean l;
			if((l = session.get(Listing.class, listing.getListid()).getActive()) == false) {
				log.debug(l);
				return null;
			}
			
			//MarketPlaceUser seller = listing.getOwner();
			CreditCard buyerCredit = buyer.getCreditCard();
			CreditCard ownerCredit = seller.getCreditCard();
			listing.setActive(false);
			
			session.merge(buyerCredit);
			log.debug(buyerCredit);
			session.merge(ownerCredit);
			log.debug(seller);
			//session.merge(ownerCredit);
			session.merge(listing);
			log.debug("after merge seller");
			result.setBuyerName(buyer.getPseudoname());
			log.debug("after set buyer name to result");
			result.setSellerName(listing.getOwner().getPseudoname());
			log.debug("after set seller name to result");
			result.setBoughtItem(listing);
			log.debug("after set listing to result");
			session.flush();
			tx.commit();
			log.debug(result);
			return result;
		}
	}

}
