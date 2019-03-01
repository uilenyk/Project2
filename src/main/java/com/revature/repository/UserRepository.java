package com.revature.repository;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.dto.UsersDto;
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
		
		try (Session session = sf.openSession()) {
			session.get(Users.class, user.getEmail());
		}
			return user;
		}

	public List<Users> findUserByCredentials(UsersDto user) {
		List<Users> users = null;
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			Query query = session.createQuery("From users u WHERE u.password = :password AND u.email = :email");
			query.setParameter("password", user.getPassword());
			query.setParameter("email", user.getEmail());
			users = query.getResultList();
			System.out.println(users);
			return users;
		} 
	}
	}
	// public ResponseEntity<Message> getMessages(@PathVariable int id) {

