package com.revature.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinColumn(name="message_id")
	private List<Message> messages;
	
	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinColumn(name="listing_id")
	private List<Listing> listings;
	
	@OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinColumn(name="phone_id")
	private PhoneNumber phoneNumber;
	
	@OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinColumn(name="credential_id")
	private Credential credentials;
	
	@OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinColumn(name="cc_id")
	private CreditCard creditCard;
	
	@OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinColumn(name="cc_id")
	private Address address;
	
}
