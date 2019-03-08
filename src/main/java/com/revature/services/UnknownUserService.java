package com.revature.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Credential;
import com.revature.models.MarketPlaceUser;
import com.revature.models.requests.LoginRequest;
import com.revature.repository.UnknownUserRepository;

@Service
public class UnknownUserService {

	@Autowired
	UnknownUserRepository repository;

	/**
	 * Gets an LoginRequest object from the controller. Gets the user credentials
	 * from the repository and checks if the 2 matches.
	 * 
	 * @param loginRequest: object that holds a email and password
	 * @return: user if password matches and null if something doesn't match
	 */
	public MarketPlaceUser auth(LoginRequest loginRequest) {
		System.out.println(loginRequest);
		String givenPassword = loginRequest.getPassword();
		Credential login = repository.auth(loginRequest.getEmail());
		if (login == null) {
			return null;
		}
		if (BCrypt.checkpw(givenPassword, login.getPassword())/* givenPassword.equals(login.getPassword()) */) {
			return login.getMarketPlaceUser();
		}
		return null;
	}

	/**
	 * Gets a Credential object from the controller. Hashes the password with a salt
	 * and passes it to the repository
	 * 
	 * @param cred: the Credential object
	 * @return: a user on success and null if something goes wrong in the repository
	 */
	public MarketPlaceUser create(Credential cred) {
		String hashed = BCrypt.hashpw(cred.getPassword(), BCrypt.gensalt(12));
		cred.setPassword(hashed);
		MarketPlaceUser user = repository.createUser(cred);
		if (user == null) {
			return null;
		} else {
			return user;
		}
	}

}
