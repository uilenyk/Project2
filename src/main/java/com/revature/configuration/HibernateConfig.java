package com.revature.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.revature.models.Address;
import com.revature.models.Credential;
import com.revature.models.CreditCard;
import com.revature.models.Listing;
import com.revature.models.MarketPlaceUser;
import com.revature.models.Message;
import com.revature.models.PhoneNumber;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		System.out.println("Configuring session factory");
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setConfigLocation(new ClassPathResource("hibernate.cfg.xml"));

		// Set annotated Classes
		Class[] models = { MarketPlaceUser.class, Address.class, Credential.class, CreditCard.class, Listing.class,
				PhoneNumber.class, Message.class};
		factoryBean.setAnnotatedClasses(models);
		factoryBean.setDataSource(getDataSource());
		return factoryBean;
	}

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		System.out.println("Configuring data source");
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl(System.getenv("PROJECT2_URL"));
		dataSource.setUsername(System.getenv("PROJECT2_USER"));
		dataSource.setPassword(System.getenv("PROJECT2_PASS"));
		return dataSource;
	}

	@Bean
	public HibernateTransactionManager getTransactionManager() {
		System.out.println("Configuring transaction manager");
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}

}
