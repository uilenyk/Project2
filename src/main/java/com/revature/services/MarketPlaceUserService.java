package com.revature.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.revature.models.MarketPlaceUser;
import com.revature.repository.MarketPlaceUserRepository;

@Service
public class MarketPlaceUserService {
	private static Logger log = Logger.getRootLogger();

	@Autowired
	private MarketPlaceUserRepository repository;

	public MarketPlaceUser create(MarketPlaceUser marketPlaceUser) {
		return repository.create(marketPlaceUser);
	}
	
	public MarketPlaceUser findBy(int id) {
		log.debug("user id in mpu service: "+id);
		log.debug(repository);
		MarketPlaceUser user = repository.findBy(id);
		user.setSentMessages(null);
		user.setReceivedMessages(null);
		return user;
	}

	public MarketPlaceUser updateUser(MarketPlaceUser user) {
		System.out.println(user.toString());
		MarketPlaceUser u = repository.update(user);
		if(u != null) {
			return u;
		} else {
			return null;
		}
		
	}

	public void messageAlert(MarketPlaceUser receiver) {
		receiver.setNewMessage(true);
		repository.messageAlert(receiver);
		
	}

	public MarketPlaceUser findByPsudoname(String userName) {
		MarketPlaceUser user = repository.findByPsudoname(userName);
		return user;
	}

//	public MarketPlaceUser findUserByCredentials(LoginRequest loginRequest) {
//		return repository.findUserByCredentials(loginRequest);
//	}

}