package com.revature.services;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Listing;
import com.revature.repository.ListingRepository;

@Service
public class ListingService {

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

	/*
	 * Set the listing active true and some timeout
	 * 
	 */
	public Listing create(Listing listing) {
//		listing.setActive(true);
//		listing.setTimeout(null);
		return repository.create(listing);
	}

	/*
	 * Set the listing active and a new timeout
	 */
	public Listing update(Listing listing) {
//		listing.setActive(true);
//		listing.setTimeout(null);
		return repository.update(listing);
	}

	public void delete(int listid) {
		repository.delete(listid);
	}

	public List<Listing> filterByActivity(boolean active, List<Listing> targetList) {
		Predicate<Listing> byActivity = li -> li.getActive() == active;
		return targetList.stream().filter(byActivity).collect(Collectors.<Listing>toList());
	}
}
