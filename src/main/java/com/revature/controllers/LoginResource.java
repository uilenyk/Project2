package com.revature.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.User;

@RestController
public class LoginResource {

	@PostMapping(path = "/login")
	public ResponseEntity<User> login() {
		/*
		 * 
		 * Call the service class to retrieve the user from the persistent database
		 * 
		 */
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
