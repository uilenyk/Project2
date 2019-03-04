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

	/**
	 * Creates the start of a conversation. This message will not have a parent
	 * @param newMessage: the message to the saved in the database
	 * @param receiverId: id of the recipent, in future will be turned into their psudoname
	 * @return: returns the message on success and null of failure
	 */
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
//		mpus.setViewedMessages(int userId);
		return results;
	}
	
	public List<Message> getSentMessages(int userId) {
		List<Message> results = repository.getSentMessages(userId);
		return results;
	}

	/**
	 * Pushes a reply to a message to the db
	 * @param replyMessage: the replay message
	 * @param parentId: the id of the message replyMessage is replying to
	 * @param id: the id of the user sending this message
	 * @return: returns the message on success and null on failure
	 */
	public Message reply(Message replyMessage, int parentId, int id) {
		MarketPlaceUser receiver = mpus.findBy(id);
		replyMessage.setReceiver(receiver);
		Message message = repository.createMessage(replyMessage, parentId);
		if(message != null)
			return message;
		else
			return null;
	}
}
