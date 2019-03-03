package com.revature.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.revature.models.MarketPlaceUser;
import com.revature.models.Message;
import com.revature.repository.MessageRepository;

@Service
public class MessageService {
	private static Logger log = Logger.getRootLogger();

	@Autowired
	private MessageRepository repository;
	
	@Autowired
	private MarketPlaceUserService mpus;

	public Message createMessage(Message newMessage, int receiverId) {
		log.debug("receiver id: "+receiverId);
		MarketPlaceUser receiver = mpus.findBy(receiverId);
		log.debug("the receiver: "+receiver);
		newMessage.setReceiver(receiver);
		log.debug("in message service: "+newMessage.toString());
		Message message = repository.createMessage(newMessage, 0);
		if(message != null) {
			mpus.messageAlert(newMessage.getReceiver());
			return message;
		} else {
			return null;
		}
	}

	public List<Message> getMessages(int userId) {
		List<Message> results = repository.getMessages(userId);
		return results;
	}

	public Message reply(Message replyMessage, int parentId, int id) {
		MarketPlaceUser receiver = mpus.findBy(id);
		replyMessage.setReceiver(receiver);
		Message message = repository.createMessage(replyMessage, parentId);
		return message;
	}
}
