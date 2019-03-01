package com.revature.repository;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.Users;

@Repository
public class UserRepository {

	@Autowired
	EntityManagerFactory emf;

	public Users createUser(Users user) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);

		try (Session session = sf.openSession()) {
			int id = (int) session.save(user);
			user.setId(id);
			return user;
		}
	}

	public Users login(Users user) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		Users loginUser = new Users();
		try (Session session = sf.openSession()) {
			loginUser = session.get(Users.class, user.getEmail());
		}
			return loginUser;
		}
	}
	// public ResponseEntity<Message> getMessages(@PathVariable int id) {

