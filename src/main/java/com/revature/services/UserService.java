package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dto.UsersDto;
import com.revature.models.Users;
import com.revature.repository.UserRepository;

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
		return userRepository.createUser(user);
	}

	public List<Users> findUserByCredentials(UsersDto user) {
		return this.userRepository.findUserByCredentials(user);
	}

	public Users login(Users user) {
		return this.userRepository.login(user);
	}

}