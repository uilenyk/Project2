package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Message;
import com.revature.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository repository;

	public Message createMessage(Message newMessage) {
		Message message = repository.createMessage(newMessage);
		MarketPlaceUserService mpus = new MarketPlaceUserService();
		mpus.messageAlert(newMessage.getReceiver());
		if(message != null) {
			return message;
		} else {
			return null;
		}
	}
}
