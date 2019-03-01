package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Users;
import com.revature.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}


	@PostMapping("")
	public Users saveBook(@RequestBody Users user) {
		return this.userService.createUser(user);
	}
}