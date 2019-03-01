package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Users;
import com.revature.services.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class UserController {

	UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/login")
	public void login(@RequestBody Users user) {
		System.out.println("login");
		Users loginUser = this.userService.login(user);
		System.out.println(loginUser.getFirstName());
	}
	
	@PostMapping("")
	public Users saveUser(@RequestBody Users user) {
		return this.userService.createUser(user);
	}
}