package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Listing;
import com.revature.models.requests.ListingPatchRequest;
import com.revature.models.requests.MakeInactiveRequest;
import com.revature.services.ListingService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/listings")
public class ListingController {

	@Autowired
	private ListingService listingService;

	@GetMapping(path = "")
	public @ResponseBody ResponseEntity<List<Listing>> getAllListings(
			@RequestParam(value = "active", required = false, defaultValue = "true") String active) {
		List<Listing> listings = listingService.findAll(Boolean.valueOf(active));
		if (listings != null) {
			return new ResponseEntity<>(listings, HttpStatus.OK);
		}
		return new ResponseEntity<>(listings, HttpStatus.NOT_FOUND);
	}

	@GetMapping(path = "/{listid}")
	public @ResponseBody ResponseEntity<Listing> getListing(@PathVariable("listid") String listid) {
		Listing listing = listingService.findBy(Integer.parseInt(listid));
		if (listing != null) {
			return new ResponseEntity<>(listing, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@PostMapping(path = "")
	public @ResponseBody ResponseEntity<Listing> addListing(@RequestBody Listing listing) {
		Listing createdListing = listingService.create(listing);
		if (createdListing != null) {
			return new ResponseEntity<>(listing, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@PutMapping(path = "/update/")
	public ResponseEntity<Listing> update(@RequestBody Listing listing){
		Listing result = listingService.update(listing);
		return null;
	}

	@PatchMapping(path = "/{listid}")
	public @ResponseBody ResponseEntity<Void> editListing(@PathVariable("listid") String listid,
			@RequestBody ListingPatchRequest request) {
		request.setListid(Integer.parseInt(listid));
		listingService.patch(request);
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@PatchMapping(path = "/{listid}/active")
	public @ResponseBody ResponseEntity<Void> makeListingInactive(@PathVariable("listid") String listid,
			@RequestBody MakeInactiveRequest makeInactiveRequest) {
		makeInactiveRequest.setListid(Integer.parseInt(listid));
		listingService.patch(makeInactiveRequest);
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(path = "/{listid}")
	public @ResponseBody ResponseEntity<Void> deleteListing(@PathVariable("listid") String listid) {
		listingService.delete(Integer.parseInt(listid));
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

}
