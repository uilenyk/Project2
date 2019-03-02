package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Credential;
import com.revature.models.MarketPlaceUser;
import com.revature.models.requests.LoginRequest;
import com.revature.services.UnknownUserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/unknown")
public class UnknownUserController {

	@Autowired
	private UnknownUserService service;

	@PostMapping(path="/login")
	public ResponseEntity<MarketPlaceUser> login(@RequestBody LoginRequest loginRequest) {
		MarketPlaceUser marketPlaceUser = service.auth(loginRequest);
		if (marketPlaceUser != null) {
			return new ResponseEntity<MarketPlaceUser>(marketPlaceUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<MarketPlaceUser> signup(@RequestBody Credential cred){
		MarketPlaceUser user = service.create(cred);
		if (user != null) {
			return new ResponseEntity<MarketPlaceUser>(user, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
}