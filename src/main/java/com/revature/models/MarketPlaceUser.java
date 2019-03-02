package com.revature.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "MarketPlaceUser.findAll", query = "SELECT m FROM MarketPlaceUser m"),
	@NamedQuery(name = "MarketPlaceUser.findPasswordByUserCredentials", query = "SELECT mpu FROM MarketPlaceUser mpu WHERE mpu.email = :email") 
	})
public class MarketPlaceUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mpuid;

	private String firstname;

	private String lastname;

	private String pseudoname;

	// bi-directional one-to-one association to Address
	@OneToOne(mappedBy = "marketPlaceUser")
	private Address address;

	// bi-directional one-to-one association to Credential
	@OneToOne(mappedBy = "marketPlaceUser")
	private Credential credential;

	// bi-directional one-to-one association to CreditCard
	@OneToOne(mappedBy = "marketPlaceUser")
	private CreditCard creditCard;

	// bi-directional one-to-one association to PhoneNumber
	@OneToOne(mappedBy = "marketPlaceUser")
	private PhoneNumber phoneNumber;

	// bi-directional many-to-one association to MarketPlaceUserListing
	@OneToMany(mappedBy = "marketPlaceUser")
	private List<MarketPlaceUserListing> marketPlaceUserListings;

	public MarketPlaceUser() {
	}

	public Integer getMpuid() {
		return this.mpuid;
	}

	public void setMpuid(Integer mpuid) {
		this.mpuid = mpuid;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPseudoname() {
		return this.pseudoname;
	}

	public void setPseudoname(String pseudoname) {
		this.pseudoname = pseudoname;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Credential getCredential() {
		return this.credential;
	}

	public void setCredentials(Credential credential) {
		this.credential = credential;
	}

	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public PhoneNumber getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumbers(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<MarketPlaceUserListing> getMarketPlaceUserListings() {
		return this.marketPlaceUserListings;
	}

	public void setMarketPlaceUserListings(List<MarketPlaceUserListing> marketPlaceUserListings) {
		this.marketPlaceUserListings = marketPlaceUserListings;
	}

	public MarketPlaceUserListing addMarketPlaceUserListing(MarketPlaceUserListing marketPlaceUserListing) {
		getMarketPlaceUserListings().add(marketPlaceUserListing);
		marketPlaceUserListing.setMarketPlaceUser(this);
		return marketPlaceUserListing;
	}

	public MarketPlaceUserListing removeMarketPlaceUserListing(MarketPlaceUserListing marketPlaceUserListing) {
		getMarketPlaceUserListings().remove(marketPlaceUserListing);
		marketPlaceUserListing.setMarketPlaceUser(null);
		return marketPlaceUserListing;
	}

}
