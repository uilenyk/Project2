package com.revature.controllers;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Listing;
import com.revature.models.MarketPlaceUser;
import com.revature.services.MarketPlaceUserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/market-place-users")
public class MarketPlaceUserController {

	@Autowired
	private MarketPlaceUserService marketPlaceUserService;

	@Autowired
	private ListingService listingService;

	@GetMapping(path = "/{mpuid}")
	public @ResponseBody ResponseEntity<MarketPlaceUser> getMarketPlaceUser(@PathVariable("mpuid") String mpuid) {
		MarketPlaceUser marketPlaceUser = marketPlaceUserService.findBy(Integer.parseInt(mpuid));
		if (marketPlaceUser != null) {
			return new ResponseEntity<>(marketPlaceUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/{mpuid}/listings")
	public @ResponseBody ResponseEntity<List<Listing>> getMarketPlaceUserListings(@PathVariable("mpuid") String mpuid,
			@RequestParam(value = "active", required = false, defaultValue = "true") String active) {
		MarketPlaceUser marketPlaceUser = marketPlaceUserService.findBy(Integer.parseInt(mpuid));
		if (marketPlaceUser != null) {
			List<Listing> listings = marketPlaceUser.getListings();
			List<Listing> results = listingService.filterByActivity(Boolean.valueOf(active), listings);
			return new ResponseEntity<>(results, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@GetMapping(path = "/{mpuid}/listings/{listid}")
	public @ResponseBody ResponseEntity<Listing> getMarketPlaceUserListing(@PathVariable("mpuid") String mpuid,
			@PathVariable("listid") String listid) {
		int listId = Integer.parseInt(listid);
		MarketPlaceUser marketPlaceUser = marketPlaceUserService.findBy(Integer.parseInt(mpuid));
		if (marketPlaceUser != null) {
			List<Listing> listings = marketPlaceUser.getListings();
			Predicate<Listing> byListId = (li -> li.getListid() == listId);
			List<Listing> results = listings.stream().filter(byListId).collect(Collectors.<Listing>toList());
			return new ResponseEntity<>(results.get(0), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@PutMapping(path="/personal/update")
	public @ResponseBody ResponseEntity<?> updateUser(@RequestBody MarketPlaceUser user) {
		System.out.println(user.toString());
		MarketPlaceUser updatedUser = service.updateUser(user);
		if (updatedUser != null) {
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
		}
	}

}
