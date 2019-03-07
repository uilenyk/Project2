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
import com.revature.models.reponse.BuyerReceipt;
import com.revature.models.requests.ListingPatchRequest;
import com.revature.models.requests.MakeInactiveRequest;
import com.revature.repository.ListingRepository;

@Service
public class ListingService {
	private Logger log = Logger.getRootLogger();

//	@Autowired
//	private TagService tagService;
	@Autowired 
	private MarketPlaceUserService mpus;

	@Autowired
	private ListingRepository repository;

	public Listing findBy(int id) {
		return repository.findBy(id);
	}

	public List<Listing> findAll() {
		return repository.findAll();
	}

	public List<Listing> findAll(boolean active) {
		return filterByActivity(active, repository.findAll());
	}

	public Listing create(Listing listing) {
		listing.setLife(30, true);
		System.out.println("Tags: " + listing.getTags());
		return repository.create(listing);
	}

	public Listing update(Listing listing) {
		listing.resetLife(LocalDateTime.now());
		return repository.update(listing);
	}

	public void patch(ListingPatchRequest request) {
		repository.patch(request);
	}

	public void patch(MakeInactiveRequest request) {
		repository.patch(request);
	}

	public void delete(int listid) {
		repository.delete(listid);
	}

	public List<Listing> filterByActivity(boolean active, List<Listing> targetList) {
		Predicate<Listing> byActivity = li -> li.getActive() == active;
		return targetList.stream().filter(byActivity).collect(Collectors.<Listing>toList());
	}

	public BuyerReceipt buyListing(Listing listing, int buyerId) throws HttpClientErrorException {
		if(listing.getOwner().getMpuid() == buyerId) {
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "Cannot sell to yourself!");
		}
		MarketPlaceUser buyer = mpus.findBy(buyerId);
		if(buyer.getCreditCard().getBalance().compareTo(listing.getPrice()) < 0) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Insufficent Funds");
		}
		MarketPlaceUser seller = mpus.findBy(listing.getOwner().getMpuid());
		//listing.setOwner(seller);
		CreditCard buyerCredit = buyer.getCreditCard();
		CreditCard ownerCredit = seller.getCreditCard();
		buyerCredit.setBalance(buyerCredit.getBalance().subtract(listing.getPrice()));
		ownerCredit.setBalance(ownerCredit.getBalance().add(listing.getPrice()));
		BuyerReceipt receipt = repository.buyListing(listing, buyer, seller);
		log.debug(receipt);
		if(receipt == null) {
			throw new HttpClientErrorException(HttpStatus.GONE, "Listing no longer available");
		}
		return receipt;
	}

}
