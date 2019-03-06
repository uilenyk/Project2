package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Listing;
import com.revature.repository.ListingRepository;

@Service
public class ListingService {

	@Autowired
	private ListingRepository repository;

	public Listing findListingById(int id) {
		return repository.findListingById(id);
	}

	public List<Listing> findAllListings() {
		return repository.findAllListings();
	}

	public Listing create(Listing listing) {
		return repository.create(listing);
	}

	public void delete(Listing listing) {
		repository.delete(listing);
	}

	public Listing update(Listing listing) {
		Listing result = repository.update(listing);
		return null;
	}
}
