package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Listing;
import com.revature.services.ListingService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/listings")
public class ListingController {

	@Autowired
	private ListingService service;

	@GetMapping(path = "")
	public @ResponseBody ResponseEntity<List<Listing>> findAllListings() {
		List<Listing> listings = service.findAllListings();
		return new ResponseEntity<>(listings, HttpStatus.OK);
	}

	@PostMapping(path = "")
	public @ResponseBody ResponseEntity<Listing> create(@RequestBody Listing listing) {
		if (service.create(listing) != null) {
			return new ResponseEntity<>(listing, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@DeleteMapping(path = "/{listid}")
	public @ResponseBody ResponseEntity<Void> delete(@PathVariable("listid") int listid) {

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
