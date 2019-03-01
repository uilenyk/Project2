package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Users;
import com.revature.repositories.UserRepository;

@Service
public class UserService {

	UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}


	// Transaction Propagation
	// Concept: Contextual Sessions
	public Users createUser(Users user) {
		// Some internal business logic: validation, etc.
		return userRepository.createBook(user);
	}

}