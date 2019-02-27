package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.User;
import com.revature.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	private UserService us;
	
	@Autowired
	public UserController(UserService userService) {
		us = userService;
	}
	
	@GetMapping("/{id}")
	public String handleGet(int id) {//name of method can be anything
		return "hello";
	}
	
	@PostMapping("")
	public String getPost(@RequestBody User user) {
		return "hello";
	}
	
}
