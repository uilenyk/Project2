package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.MarketPlaceUser;
import com.revature.models.requests.LoginRequest;
import com.revature.services.MarketPlaceUserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/login")
public class MarketPlaceUserLoginController {

	@Autowired
	private MarketPlaceUserService service;

	@PostMapping(path="")
	public ResponseEntity<MarketPlaceUser> login(@RequestBody LoginRequest loginRequest) {
		MarketPlaceUser marketPlaceUser = service.findUserByCredentials(loginRequest);
		if (marketPlaceUser != null) {
			return new ResponseEntity<MarketPlaceUser>(marketPlaceUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

}