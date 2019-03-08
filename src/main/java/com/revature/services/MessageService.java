package com.revature.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	 * 
	 * @param newMessage: the message to the saved in the database
	 * @param receiveruserName: psudoname of the receiver
	 * @return: returns the message on success and null of failure
	 */
	public ResponseEntity<Message> createMessage(Message newMessage, String userName) {
		log.debug("receiver psudoname: " + userName);
		MarketPlaceUser receiver = mpus.findByPsudoname(userName);
		if (receiver == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		// don't need the listings or messages of the user. this is to avoid the lazy
		// init exception
		receiver.setListings(null);
		receiver.setSentMessages(null);
		receiver.setReceivedMessages(null);
		newMessage.setReceiver(receiver);
		// log.debug("in message service: "+newMessage.toString());
		Message message = repository.createMessage(newMessage, 0);
		if (message != null) {
			// mpus.messageAlert(newMessage.getReceiver());
			return new ResponseEntity<>(message, HttpStatus.CREATED);
		} else {
			return null;
		}
	}

	/**
	 * Gets a user id from the controller and calls the getMessages(id) function of
	 * the repository.
	 * 
	 * @param userId: id of the user the request is coming form
	 * @return: a lost of messages that was sent to the user
	 */
	public List<Message> getMessages(int userId) {
		List<Message> results = repository.getMessages(userId);
		log.debug(results.get(0).getReceiver().getPhoneNumber().toString());
		// future feature
//		mpus.setViewedMessages(int userId);
		return results;
	}

	/**
	 * Gets an user id from the controller and sends it to the repository
	 * 
	 * @param userId: id of the user who is sending the request
	 * @return: list of messages sent by the user
	 */
	public List<Message> getSentMessages(int userId) {
		List<Message> results = repository.getSentMessages(userId);
		return results;
	}

	/**
	 * Pushes a reply to a message to the db
	 * 
	 * @param replyMessage: the replay message
	 * @param parentId: the id of the message replyMessage is replying to
	 * @param id: the id of the user sending this message
	 * @return: returns the message on success and null on failure
	 */
	public ResponseEntity<Message> reply(Message replyMessage, int parentId, String userName) {
		MarketPlaceUser receiver = mpus.findByPsudoname(userName);
		if (receiver == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		// don't need the listings or messages of the user. this is to avoid the lazy
		replyMessage.setReceiver(receiver);
		Message message = repository.createMessage(replyMessage, parentId);
		if (message != null)
			return new ResponseEntity<>(message, HttpStatus.CREATED);
		else
			return null;
	}
}
