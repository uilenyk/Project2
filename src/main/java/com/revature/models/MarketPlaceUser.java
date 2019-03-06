package com.revature.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "market_place_user")
@NamedQueries({ @NamedQuery(name = "MarketPlaceUser.findAll", query = "SELECT m FROM MarketPlaceUser m") })
public class MarketPlaceUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mpuid;

	@NotNull
	@Column(length = 50)
	private String firstname;

	@NotNull
	@Column(length = 50)
	private String lastname;

	@NotNull
	@Column(length = 50, unique = true)
	private String pseudoname;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "mpu_address_id")
	private Address address;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "credit_card_id")
	private CreditCard creditCard;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "phone_number_id")
	private PhoneNumber phoneNumber;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "mpu_id")
	@JsonBackReference(value = "listings")
	private List<Listing> listings;

	public MarketPlaceUser() {
	}

	public int getMpuid() {
		return mpuid;
	}

	public void setMpuid(int mpuid) {
		this.mpuid = mpuid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPseudoname() {
		return pseudoname;
	}

	public void setPseudoname(String pseudoname) {
		this.pseudoname = pseudoname;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Listing> getListings() {
		return listings;
	}

	public void setListings(List<Listing> listings) {
		this.listings = listings;
	}

	@Override
	public String toString() {
		return "MarketPlaceUser [mpuid=" + mpuid + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", pseudoname=" + pseudoname + ", address=" + address + ", creditCard=" + creditCard
				+ ", phoneNumber=" + phoneNumber + ", listings=" + listings + "]";
	}

}
