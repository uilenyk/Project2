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
	
	/*
	 * NOTE IN FUTURE, SHOULD ADD FUNCTION FOR GETTING RECEIVER BY PSUDONAME INSTEAD OF THEIR ID
	 */
	
	
	@PostMapping("/send/{receiver_username}")
	public ResponseEntity<Message> sendMessage(@RequestBody Message newMessage, @PathVariable("receiver_username") String receiverName) {
		ResponseEntity<Message> response = service.createMessage(newMessage, receiverName);
		if (response != null) {
			return response;
		} else {
			return new ResponseEntity<>(null, HttpStatus.resolve(500));
		}
	}
	
	@PostMapping("/reply/{id}/{receiver_username}")
	public ResponseEntity<Message> reply(@PathVariable("id") int parentId, @PathVariable("receiver_username") String userName, @RequestBody Message replyMessage){
		ResponseEntity<Message> message = service.reply(replyMessage, parentId, userName);
		if(message != null)
			return message;
		else
			return new ResponseEntity<>(null, HttpStatus.resolve(500));
	}
	
	@GetMapping("/received/{id}")
	public ResponseEntity<List<Message>> getMessages(@PathVariable("id") String id){
		int userId = Integer.parseInt(id);
		List<Message> results = service.getMessages(userId);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}
	
	@GetMapping("/sent/{id}")
	public ResponseEntity<List<Message>> getSentMessages(@PathVariable("id") String id){
		int userId = Integer.parseInt(id);
		List<Message> results = service.getMessages(userId);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}

}
