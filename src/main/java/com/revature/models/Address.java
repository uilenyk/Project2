package com.revature.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Positive;

@Entity
@NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String city;

	private String state;

	private String streetname;

	@Positive
	private Integer streetnumber;

	@Positive
	private Integer zipcode;

	public Address() {
	}

	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * 
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 
	 * @return
	 */
	public String getState() {
		return this.state;
	}

	/**
	 * 
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 
	 * @return
	 */
	public String getStreetname() {
		return this.streetname;
	}

	/**
	 * 
	 * @param streetname
	 */
	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getStreetnumber() {
		return this.streetnumber;
	}

	/**
	 * 
	 * @param streetnumber
	 */
	public void setStreetnumber(Integer streetnumber) {
		this.streetnumber = streetnumber;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getZipcode() {
		return this.zipcode;
	}

	/**
	 * 
	 * @param zipcode
	 */
	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", city=" + city + ", state=" + state + ", streetname=" + streetname
				+ ", streetnumber=" + streetnumber + ", zipcode=" + zipcode + "]";
	}
}
