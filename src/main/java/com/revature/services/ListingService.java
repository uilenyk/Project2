package com.revature.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Listing;
import com.revature.models.requests.ListingPatchRequest;
import com.revature.models.requests.MakeInactiveRequest;
import com.revature.repository.ListingRepository;

@Service
public class ListingService {

	@Autowired
	private TagService tagService;

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

}
