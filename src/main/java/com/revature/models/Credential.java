package com.revature.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQuery(name = "Credential.findAll", query = "SELECT c FROM Credential c")
public class Credential implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String email;

	private String password;

	private String salt;

	// bi-directional one-to-one association to MarketPlaceUser
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mpuid")
	private MarketPlaceUser marketPlaceUser;

	public Credential(String email, String password, String salt, MarketPlaceUser marketPlaceUser) {
		super();
		this.email = email;
		this.password = password;
		this.salt = salt;
		this.marketPlaceUser = marketPlaceUser;
	}

	public String getSalt() {
		return this.salt;
	}

	public Credential() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((marketPlaceUser == null) ? 0 : marketPlaceUser.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((salt == null) ? 0 : salt.hashCode());
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
		Credential other = (Credential) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (marketPlaceUser == null) {
			if (other.marketPlaceUser != null)
				return false;
		} else if (!marketPlaceUser.equals(other.marketPlaceUser))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (salt == null) {
			if (other.salt != null)
				return false;
		} else if (!salt.equals(other.salt))
			return false;
		return true;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public MarketPlaceUser getMarketPlaceUser() {
		return this.marketPlaceUser;
	}

	public void setMarketPlaceUser(MarketPlaceUser marketPlaceUser) {
		this.marketPlaceUser = marketPlaceUser;
	}

}
