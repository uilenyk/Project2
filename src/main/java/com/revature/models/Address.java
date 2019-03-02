package com.revature.models;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	private String city;

	private String state;

	private String streetname;

	private Integer streetnumber;

	private Integer zipcode;

	// bi-directional one-to-one association to MarketPlaceUser
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mpuid")
	private MarketPlaceUser marketPlaceUser;

	public Address() {
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreetname() {
		return this.streetname;
	}

	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}

	public Integer getStreetnumber() {
		return this.streetnumber;
	}

	public void setStreetnumber(Integer streetnumber) {
		this.streetnumber = streetnumber;
	}

	public Integer getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	public MarketPlaceUser getMarketPlaceUser() {
		return this.marketPlaceUser;
	}

	public void setMarketPlaceUser(MarketPlaceUser marketPlaceUser) {
		this.marketPlaceUser = marketPlaceUser;
	}

}
