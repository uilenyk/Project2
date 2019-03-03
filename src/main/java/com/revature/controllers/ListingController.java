package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public @ResponseBody ResponseEntity<List<Listing>> getAllListings(
			@RequestParam(value = "active", required = false, defaultValue = "true") String active) {
		List<Listing> listings = service.findAll(Boolean.valueOf(active));
		return new ResponseEntity<>(listings, HttpStatus.OK);
	}

	@GetMapping(path = "/{listid}")
	public @ResponseBody ResponseEntity<Listing> getListing(@PathVariable("listid") String listid) {
		Listing listing = service.findBy(Integer.parseInt(listid));
		if (listing != null) {
			return new ResponseEntity<>(listing, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@PostMapping(path = "")
	public @ResponseBody ResponseEntity<Listing> createListing(@RequestBody Listing listing) {
		if (service.create(listing) != null) {
			return new ResponseEntity<>(listing, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
	}

	@PutMapping(path = "/{listid}")
	public @ResponseBody ResponseEntity<Listing> updateListing(@PathVariable("listid") String listid,
			@RequestBody Listing newListing) {
		Listing listing = service.findBy(Integer.parseInt(listid));
		if (listing != null) {
			listing.setDescription(newListing.getDescription());
			listing.setName(newListing.getName());
			listing.setPrice(newListing.getPrice());
			listing.setTags(newListing.getTags());
			return new ResponseEntity<>(service.update(listing), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

}
