package com.revature.services;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Tag;
import com.revature.repository.TagRepository;

@Service
public class TagService {

	@Autowired
	private TagRepository repository;

	/**
	 * Retrieves Tag by id
	 * 
	 * @param id
	 * @return
	 */
	public Tag findBy(int id) {
		return repository.findBy(id);
	}

	/**
	 * Retrieves all Tags
	 * 
	 * @return
	 */
	public List<Tag> findAll() {
		return repository.findAll();
	}

	/**
	 * Retrieves all Tags by a tag name
	 * 
	 * @param tagName
	 * @return
	 */
	public List<Tag> findAll(String tagName) {
		return filterByTagName(tagName, repository.findAll());
	}

	/**
	 * Creates a Tag in the database
	 * 
	 * @param tag
	 * @return
	 */
	public Tag create(Tag tag) {
		if (findAll(tag.getTagName()).isEmpty()) {
			return repository.create(tag);
		}
		return null;
	}

	/**
	 * Filters list of Tags by the tag name
	 * 
	 * @param tagName
	 * @param targetList
	 * @return
	 */
	public List<Tag> filterByTagName(String tagName, List<Tag> targetList) {
		Predicate<Tag> byTagName = tag -> tag.getTagName().equals(tagName);
		return targetList.stream().filter(byTagName).collect(Collectors.<Tag>toList());
	}

}
