package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.UsersDto;
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
	public List<Users> login(@RequestBody UsersDto user) {
		System.out.println("login");
		System.out.println(user.getEmail());
		List<Users> users = this.userService.findUserByCredentials(user);
		System.out.println(users);
		return users;
	}
	
	@PostMapping("")
	public Users saveUser(@RequestBody Users user) {
		return this.userService.createUser(user);
	}
}