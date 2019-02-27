package com.revature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.revature.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private static UserService us = new UserService();
	
}
