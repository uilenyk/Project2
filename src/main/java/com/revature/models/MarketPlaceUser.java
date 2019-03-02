package com.revature.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.revature.models.Address;

@Entity
@Table(name = "market_place_user")
@NamedQueries({ @NamedQuery(name = "MarketPlaceUser.findAll", query = "SELECT m FROM MarketPlaceUser m")})
public class MarketPlaceUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mpuid;

	private String firstname;

	private String lastname;

	private String pseudoname;

	// bi-directional one-to-one association to Address
	@OneToOne
	@JoinColumn(name="mpu_address_id")
	private Address address;

	// bi-directional one-to-one association to CreditCard
	@OneToOne
	@JoinColumn(name="credit_card_id")
	private CreditCard creditCard;

	// bi-directional one-to-one association to PhoneNumber
	@OneToOne
	@JoinColumn(name="phone_number_id")
	private PhoneNumber phoneNumber;

	// bi-directional many-to-one association to MarketPlaceUserListing
	@OneToMany
	@JoinColumn(name="mpu_id")
	private List<Listing> marketPlaceUserListings;

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

	public List<Listing> getMarketPlaceUserListings() {
		return marketPlaceUserListings;
	}

	public void setMarketPlaceUserListings(List<Listing> marketPlaceUserListings) {
		this.marketPlaceUserListings = marketPlaceUserListings;
	}

	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
