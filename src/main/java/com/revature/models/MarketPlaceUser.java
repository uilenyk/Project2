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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "market_place_user")
@NamedQueries({ @NamedQuery(name = "MarketPlaceUser.findAll", query = "SELECT m FROM MarketPlaceUser m") })
public class MarketPlaceUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mpuid;

	@Column(length = 50)
	private String firstname;
	@Column(length = 50)
	private String lastname;
	@Column(length = 50, nullable = false, unique = true)
	private String pseudoname;
	// has the user gotten a new message
	@Column(name = "new_message", columnDefinition = "boolean default false")
	private boolean newMessage;

	// bi-directional one-to-one association to Address
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "mpu_address_id")
	private Address address;

	// bi-directional one-to-one association to CreditCard
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "credit_card_id")
	private CreditCard creditCard;

	// bi-directional one-to-one association to PhoneNumber
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "phone_number_id")
	private PhoneNumber phoneNumber;

	// bi-directional many-to-one association to MarketPlaceUserListing
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "mpu_id")
	@JsonBackReference
	private List<Listing> marketPlaceUserListings;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_id")
	@JsonBackReference(value = "sent_messages")
	private List<Message> sentMessages;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "receiver_id")
	@JsonBackReference(value = "received_messages")
	private List<Message> receivedMessages;

	public MarketPlaceUser() {
	}

	public MarketPlaceUser(int mpuid, String firstname, String lastname, String pseudoname, boolean newMessage,
			Address address, CreditCard creditCard, PhoneNumber phoneNumber, List<Listing> marketPlaceUserListings,
			List<Message> sentMessages, List<Message> receivedMessages) {
		super();
		this.mpuid = mpuid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.pseudoname = pseudoname;
		this.newMessage = newMessage;
		this.address = address;
		this.creditCard = creditCard;
		this.phoneNumber = phoneNumber;
		this.marketPlaceUserListings = marketPlaceUserListings;
		this.sentMessages = sentMessages;
		this.receivedMessages = receivedMessages;
	}

	public boolean isNewMessage() {
		return newMessage;
	}

	public void setNewMessage(boolean newMessage) {
		this.newMessage = newMessage;
	}

	public List<Message> getSentMessages() {
		return sentMessages;
	}

	public void setSentMessages(List<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}

	public List<Message> getReceivedMessages() {
		return receivedMessages;
	}

	public void setReceivedMessages(List<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
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
		result = prime * result + (newMessage ? 1231 : 1237);
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((pseudoname == null) ? 0 : pseudoname.hashCode());
		result = prime * result + ((receivedMessages == null) ? 0 : receivedMessages.hashCode());
		result = prime * result + ((sentMessages == null) ? 0 : sentMessages.hashCode());
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
		if (newMessage != other.newMessage)
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
		if (receivedMessages == null) {
			if (other.receivedMessages != null)
				return false;
		} else if (!receivedMessages.equals(other.receivedMessages))
			return false;
		if (sentMessages == null) {
			if (other.sentMessages != null)
				return false;
		} else if (!sentMessages.equals(other.sentMessages))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MarketPlaceUser [mpuid=" + mpuid + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", pseudoname=" + pseudoname + ", newMessage=" + newMessage + ", address=" + address + ", creditCard="
				+ creditCard + ", phoneNumber=" + phoneNumber + ", marketPlaceUserListings=" + marketPlaceUserListings
				+ ", sentMessages=" + sentMessages + ", receivedMessages=" + receivedMessages + "]";
	}

}
