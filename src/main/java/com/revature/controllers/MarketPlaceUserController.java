package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Listing;
import com.revature.models.MarketPlaceUser;
import com.revature.services.ListingService;
import com.revature.services.MarketPlaceUserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/market-place-users")
public class MarketPlaceUserController {

	@Autowired
	private MarketPlaceUserService service;

	@GetMapping(path = "/{mpuid}")
	public @ResponseBody ResponseEntity<MarketPlaceUser> findMarketPlaceUserById(@PathVariable("mpuid") String mpuid) {
		MarketPlaceUser marketPlaceUser = service.findBy(Integer.parseInt(mpuid));
		if (marketPlaceUser != null) {
			return new ResponseEntity<>(marketPlaceUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/{mpuid}/listings/{listid}")
	public @ResponseBody ResponseEntity<Listing> findMarketPlaceUserListingsById(
			@PathVariable("mpuid") String mpuid,
			@PathVariable("listid") String listid) {
		int listId = Integer.parseInt(listid);
		MarketPlaceUser marketPlaceUser = service.findBy(Integer.parseInt(mpuid));
		if (marketPlaceUser != null) {
			List<Listing> listings = marketPlaceUser.getMarketPlaceUserListings();
			for (int i = 0; i < listings.size(); i++) {
				Listing listing = listings.get(i);
				if (listing.getListid().equals(listId)) {
					return new ResponseEntity<>(listing, HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@PutMapping("/personal/update")
	public @ResponseBody ResponseEntity<?> updateUser(@RequestBody MarketPlaceUser user) {
		System.out.println(user.toString());
		MarketPlaceUser updatedUser = marketPlaceUserService.updateUser(user);
		if (updatedUser != null) {
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
		}
	}

}
