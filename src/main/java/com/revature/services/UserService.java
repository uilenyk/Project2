package com.revature.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.User;
import com.revature.repository.UserRepository;
import com.revature.util.HibernateUtil;

/**
 * Native Queries - Standard SQL queries. Can be used when Hibernate does not support the 
 * method of querying that you need. If you use a native query, you are invalidating your cache.
 * 
 * Named Queries - Queries defined on the entity definition and accessible by name.
 * @author mitch
 *
 */

@Service
public class UserService {

	UserRepository ur;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		ur = userRepository;
	}
	
	public void create(User user) {
		SessionFactory sf = HibernateUtil.getSessionFactory();

		try (Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			session.persist(user);
			//session.flush();
//			bear.setBreed("Yogi"); // This change will be reflected in the database!
			tx.commit();
		}
	}

	public User getUser(int id) {
		SessionFactory sf = HibernateUtil.getSessionFactory();

		try (Session session = sf.openSession()) {
			User user = session.get(User.class, id);
		//	Hibernate.initialize(user.getMessages());
			return user;
//			return session.load(Bear.class, id); // Gets a proxy object
		}
	}

	public User update(User user) {
		SessionFactory sf = HibernateUtil.getSessionFactory();

		try (Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
		//	User otherUser = session.get(User.class, user.getId());
			session.merge(user);
		//	otherUser.setWeight(80);
			tx.commit();
		//	return otherUser;
		}
		return user;
	}

	/*
	public List<User> getUsersMessages(String breed) {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		
		try (Session session = sf.openSession()) {
			// HQL - Hibernate Query Language
			/*
			 * A more object oriented form of SQL that largely utilizes the
			 * entity definition rather than the actual table 
			 
							  // SELECT * FROM bears WHERE breed = ?
			List<?> list = session.createQuery("select b from Bear b where b.breed like :breed")
					.setParameter("breed", breed, StringType.INSTANCE)
					.list();
			return (List<Bear>) list;
		
		}
	}
	
	public List<Bear> getBearsHeavierThan(double weight) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Bear> bearQuery = cb.createQuery(Bear.class);
			Root<Bear> root = bearQuery.from(Bear.class);
			
			bearQuery.select(root)
				.where(cb.greaterThan(root.get("weight"), weight));
			
			Query<Bear> query = session.createQuery(bearQuery);
			List<Bear> results = query.getResultList();
			return results;
		}
	}
	
	public List<Bear> getYellowBears() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createNamedQuery("getYellowBears").list();
		}

	} */
}
