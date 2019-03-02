package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.MarketPlaceUser;
import com.revature.models.requests.LoginRequest;
import com.revature.repository.MarketPlaceUserRepository;

@Service
public class MarketPlaceUserService {

	@Autowired
	private MarketPlaceUserRepository repository;

	public MarketPlaceUser create(MarketPlaceUser marketPlaceUser) {
		return repository.create(marketPlaceUser);
	}

	public MarketPlaceUser findUserByCredentials(LoginRequest loginRequest) {
		return repository.findUserByCredentials(loginRequest);
	}
}