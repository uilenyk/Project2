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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Tag;
import com.revature.services.TagService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/tags")
public class TagController {

	@Autowired
	private TagService tagService;

	@GetMapping(path = "")
	public @ResponseBody ResponseEntity<List<Tag>> getAllTags() {
		List<Tag> tags = tagService.findAll();
		if (tags != null) {
			return new ResponseEntity<>(tags, HttpStatus.OK);
		}
		return new ResponseEntity<>(tags, HttpStatus.NOT_FOUND);
	}

	@GetMapping(path = "/{tagid}")
	public @ResponseBody ResponseEntity<Tag> getTag(@PathVariable("tagid") String tagid) {
		Tag tag = tagService.findBy(Integer.parseInt(tagid));
		if (tag != null) {
			return new ResponseEntity<>(tag, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@PostMapping(path = "")
	public @ResponseBody ResponseEntity<Tag> addTag(@RequestBody Tag tag) {
		Tag createdTag = tagService.create(tag);
		if (createdTag != null) {
			return new ResponseEntity<>(tag, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
	}
}
