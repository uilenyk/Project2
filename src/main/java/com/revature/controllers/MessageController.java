package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Message;
import com.revature.services.MessageService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageService service;

	/**
	 * Gets a post request with a username and a Message object in the body. Sends
	 * to the information to the service.
	 * 
	 * @param newMessage: the message the client sent
	 * @param receiverName: the username of the recipient of the message
	 * @return: Response entity with the complete message or null if something goes
	 *          wrong
	 */
	@PostMapping("/send/{receiver_username}")
	public ResponseEntity<Message> sendMessage(@RequestBody Message newMessage,
			@PathVariable("receiver_username") String receiverName) {
		ResponseEntity<Message> response = service.createMessage(newMessage, receiverName);
		if (response != null) {
			return response;
		} else {
			return new ResponseEntity<>(null, HttpStatus.resolve(500));
		}
	}

	/**
	 * Gets a Message object from the client and sends it to the service layer.
	 * 
	 * @param parentId: id of the message this message is in response to
	 * @param userName: username of the recipient of the Message object in the body
	 * @param replyMessage: the Message object
	 * @return
	 */
	@PostMapping("/reply/{id}/{receiver_username}")
	public ResponseEntity<Message> reply(@PathVariable("id") int parentId,
			@PathVariable("receiver_username") String userName, @RequestBody Message replyMessage) {
		ResponseEntity<Message> message = service.reply(replyMessage, parentId, userName);
		if (message != null)
			return message;
		else
			return new ResponseEntity<>(null, HttpStatus.resolve(500));
	}

	/**
	 * Gets a get request from the client and sends it to the service. Expects a
	 * list of messages where the receiver_id matches user id
	 * 
	 * @param id: id of the user who sent the request
	 * @return: list of message that was sent to the user
	 */
	@GetMapping("/received/{id}")
	public ResponseEntity<List<Message>> getMessages(@PathVariable("id") String id) {
		int userId = Integer.parseInt(id);
		List<Message> results = service.getMessages(userId);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}

	/**
	 * Gets a get request from teh client and sends it to the service. Expect a list
	 * of messages where the sender_id matches the user id
	 * 
	 * @param id: id of the user sending the request
	 * @return: list of messages that the sender sent
	 */
	@GetMapping("/sent/{id}")
	public ResponseEntity<List<Message>> getSentMessages(@PathVariable("id") String id) {
		int userId = Integer.parseInt(id);
		List<Message> results = service.getSentMessages(userId);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}

}
