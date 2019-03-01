package com.revature.repository;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.Users;

@Repository
public class UserRepository {

	@Autowired
	EntityManagerFactory emf;

	
	public Users createBook(Users user) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		
		try(Session session = sf.openSession()) {
			int id = (int) session.save(user);
			user.setId(id);
			return user;
		}
	}
	
}