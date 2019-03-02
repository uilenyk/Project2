package com.revature.repository;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.Credential;
import com.revature.models.MarketPlaceUser;
import com.revature.models.requests.CreateUserRequest;

@Repository
public class UnknownUserRepository {

	@Autowired
	EntityManagerFactory emf;

	public Credential auth(String email) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);

		try (Session session = sf.openSession()) {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Credential> login = cb.createQuery(Credential.class);
			Root<Credential> root = login.from(Credential.class);

			login.select(root).where(cb.equal(root.get("email"), email));

			Query<Credential> query = session.createQuery(login);
			List<Credential> results = query.getResultList();

			if (results.isEmpty() || results == null) {
				return null;
			} else {
				return results.get(0);
			}
		}
	}

	public MarketPlaceUser createUser(CreateUserRequest cur) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);

		try (Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			int newUserId = (int) session.save(cur.getUser());
			cur.getUser().setMpuid(newUserId);
			session.persist(cur.getCredential());
			tx.commit();
			if(newUserId != 0)
				return cur.getUser();
			else 
				return null;
		}
	}

}
