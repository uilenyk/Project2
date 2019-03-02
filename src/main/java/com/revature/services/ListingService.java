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

	public Listing getListingById(int id) {
		return repository.findListingById(id);
	}

	public List<Listing> findAllListings() {
		return repository.findAllListings();
	}
}
