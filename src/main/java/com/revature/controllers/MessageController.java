package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@PostMapping("/send/{receiver_id}")
	public ResponseEntity<Message> sendMessage(@RequestBody Message newMessage, @PathVariable("receiver_id") String receiverId) {
		Message message = service.createMessage(newMessage);
		if (message != null) {
			return new ResponseEntity<>(message, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.resolve(500));
		}
	}

}
