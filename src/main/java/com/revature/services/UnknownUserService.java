package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Credential;
import com.revature.models.MarketPlaceUser;
import com.revature.models.requests.CreateUserRequest;
import com.revature.models.requests.LoginRequest;
import com.revature.repository.UnknownUserRepository;

@Service
public class UnknownUserService {

	@Autowired
	UnknownUserRepository repository;

	public MarketPlaceUser auth(LoginRequest loginRequest) {
		String givenPassword = loginRequest.getPassword();
		Credential login = repository.auth(loginRequest.getEmail());
		if (login == null) {
			return null;
		}
		if (givenPassword.equals(login.getPassword())) {
			return login.getMarketPlaceUser();
		}
		return null;
	}

	public MarketPlaceUser create(CreateUserRequest cur) {
		MarketPlaceUser user = repository.createUser(cur);
		if(user == null) {
			return null;
		} else {
			return user;
		}
	}

}
