package com.revature.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
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

@Entity
@Table(name = "market_place_user")
@NamedQueries({ @NamedQuery(name = "MarketPlaceUser.findAll", query = "SELECT m FROM MarketPlaceUser m")})
public class MarketPlaceUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mpuid;

	private String firstname;

	private String lastname;

	private String pseudoname;

	// bi-directional one-to-one association to Address
	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="mpu_address_id")
	private Address address;

	// bi-directional one-to-one association to CreditCard
	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="credit_card_id")
	private CreditCard creditCard;

	// bi-directional one-to-one association to PhoneNumber
	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="phone_number_id")
	private PhoneNumber phoneNumber;

	// bi-directional many-to-one association to MarketPlaceUserListing
	@OneToMany()
	@JoinColumn(name="mpu_id")
	private List<Listing> marketPlaceUserListings;

	public MarketPlaceUser() {
	}

	public int getMpuid() {
		return this.mpuid;
	}

	public void setMpuid(int mpuid) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((creditCard == null) ? 0 : creditCard.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((marketPlaceUserListings == null) ? 0 : marketPlaceUserListings.hashCode());
		result = prime * result + mpuid;
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((pseudoname == null) ? 0 : pseudoname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MarketPlaceUser other = (MarketPlaceUser) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (creditCard == null) {
			if (other.creditCard != null)
				return false;
		} else if (!creditCard.equals(other.creditCard))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (marketPlaceUserListings == null) {
			if (other.marketPlaceUserListings != null)
				return false;
		} else if (!marketPlaceUserListings.equals(other.marketPlaceUserListings))
			return false;
		if (mpuid != other.mpuid)
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (pseudoname == null) {
			if (other.pseudoname != null)
				return false;
		} else if (!pseudoname.equals(other.pseudoname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MarketPlaceUser [mpuid=" + mpuid + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", pseudoname=" + pseudoname + ", address=" + address + ", creditCard=" + creditCard
				+ ", phoneNumber=" + phoneNumber + ", marketPlaceUserListings=" + marketPlaceUserListings + "]";
	}

}
