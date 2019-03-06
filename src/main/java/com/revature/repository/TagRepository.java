package com.revature.repository;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.Tag;

@Repository
public class TagRepository {

	@Autowired
	private EntityManagerFactory emf;

	public Tag findBy(int id) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			Tag tag = session.find(Tag.class, id);
			return tag;
		}
	}

	public List<Tag> findAll() {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			Query<?> query = session.getNamedQuery("Tag.findAll");
			@SuppressWarnings("unchecked")
			List<Tag> tags = (List<Tag>) query.getResultList();
			return tags;
		}
	}

	public Tag create(Tag tag) {
		SessionFactory sf = emf.unwrap(SessionFactory.class);
		try (Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			int id = (int) session.save(tag);
			Tag createdTag = session.get(Tag.class, id);
			tx.commit();
			return createdTag;
		}

	}

}
