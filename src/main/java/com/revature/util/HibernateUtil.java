package com.revature.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.revature.models.Listing;
import com.revature.models.Message;
import com.revature.models.User;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	public static void configure() {
		Configuration configuration = new Configuration()
				.configure()
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Listing.class)
				.addAnnotatedClass(Message.class);
		
		configuration.setProperty("hibernate.connection.url", "${PROJECT2_URL}");
		configuration.setProperty("hibernate.connection.username", "${PROJECT2_USER}");
		configuration.setProperty("hibernate.connection.password", "${PROJECT2_PASS}");
		
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
