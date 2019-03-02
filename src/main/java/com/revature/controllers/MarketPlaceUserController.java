package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.services.ListingService;
import com.revature.services.MarketPlaceUserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/market-place-users")
public class MarketPlaceUserController {

	@Autowired
	private MarketPlaceUserService marketPlaceUserService;

	@Autowired
	private ListingService listingService;

	@GetMapping(path = "/{mpuid}/listings/{listid}")
	public @ResponseBody ResponseEntity<?> getMarketPlaceUserListingsById(@PathVariable("mpuid") String mpuid,
			@PathVariable("listid") String listid) {
		int mpuId = Integer.parseInt(mpuid);
		int listId = Integer.parseInt(listid);

		/*
		 * Write service class to find market place user listings
		 */

		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	

}
