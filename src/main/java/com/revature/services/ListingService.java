package com.revature.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.revature.models.CreditCard;
import com.revature.models.Listing;
import com.revature.models.MarketPlaceUser;
import com.revature.models.requests.ListingPatchRequest;
import com.revature.models.requests.MakeInactiveRequest;
import com.revature.models.response.BuyerReceipt;
import com.revature.repository.ListingRepository;

@Service
public class ListingService {
	private Logger log = Logger.getRootLogger();

	@Autowired
	private MarketPlaceUserService mpus;

	@Autowired
	private ListingRepository repository;

	/**
	 * Retrieves Listing by id
	 * 
	 * @param id
	 * @return
	 */
	public Listing findBy(int id) {
		return repository.findBy(id);
	}

	/**
	 * Retrieves all Listings
	 * 
	 * @return
	 */
	public List<Listing> findAll() {
		return repository.findAll();
	}

	/**
	 * Retrieves all Listings by activity
	 * 
	 * @param active
	 * @return
	 */
	public List<Listing> findAll(boolean active) {
		return filterByActivity(active, repository.findAll());
	}

	/**
	 * Creates a Listing and sets the life of the listing by default 30 days and
	 * active true
	 * 
	 * @param listing
	 * @return
	 */
	public Listing create(Listing listing) {
		listing.setLife(30, true);
		System.out.println("Tags: " + listing.getTags());
		return repository.create(listing);
	}

	/**
	 * Updates Listing and resets life of the listing based from the time
	 * 
	 * @param listing
	 * @return
	 */
	public Listing update(Listing listing) {
		listing.resetLife(LocalDateTime.now());
		return repository.update(listing);
	}

	/**
	 * Partially Updates the Listing
	 * 
	 * @param request
	 */
	public void patch(ListingPatchRequest request) {
		repository.patch(request);
	}

	/**
	 * Partially Updates the Listing
	 * 
	 * @param request
	 */
	public void patch(MakeInactiveRequest request) {
		repository.patch(request);
	}

	/**
	 * Deletes the Listing
	 * 
	 * @param listid
	 */
	public void delete(int listid) {
		repository.delete(listid);
	}

	/**
	 * Filters a target list by activity
	 * 
	 * @param active
	 * @param targetList
	 * @return
	 */
	public List<Listing> filterByActivity(boolean active, List<Listing> targetList) {
		Predicate<Listing> byActivity = li -> li.getActive() == active;
		return targetList.stream().filter(byActivity).collect(Collectors.<Listing>toList());
	}

	/**
	 * A Buyer buys from a seller their listing
	 * 
	 * @param listing
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public BuyerReceipt buyListing(Listing listing, int buyerId) throws HttpClientErrorException {
		if (listing.getOwner().getMpuid() == buyerId) {
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "Cannot sell to yourself!");
		}
		MarketPlaceUser buyer = mpus.findBy(buyerId);
		if (buyer.getCreditCard().getBalance().compareTo(listing.getPrice()) < 0) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Insufficent Funds");
		}
		MarketPlaceUser seller = mpus.findBy(listing.getOwner().getMpuid());
		CreditCard buyerCredit = buyer.getCreditCard();
		CreditCard ownerCredit = seller.getCreditCard();
		buyerCredit.setBalance(buyerCredit.getBalance().subtract(listing.getPrice()));
		ownerCredit.setBalance(ownerCredit.getBalance().add(listing.getPrice()));
		BuyerReceipt receipt = repository.buyListing(listing, buyer, seller);
		log.debug(receipt);
		if (receipt == null) {
			throw new HttpClientErrorException(HttpStatus.GONE, "Listing no longer available");
		}
		return receipt;
	}

}
