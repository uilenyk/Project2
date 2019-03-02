package com.revature.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "phone_number")
@NamedQuery(name = "PhoneNumber.findAll", query = "SELECT p FROM PhoneNumber p")
public class PhoneNumber implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "area_code_three")
	private Integer areaCodeThree;

	@Column(name = "block_four")
	private Integer blockFour;

	@Column(name = "block_three")
	private Integer blockThree;

	public PhoneNumber() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getAreaCodeThree() {
		return this.areaCodeThree;
	}

	public void setAreaCodeThree(Integer areaCodeThree) {
		this.areaCodeThree = areaCodeThree;
	}

	public Integer getBlockFour() {
		return this.blockFour;
	}

	public void setBlockFour(Integer blockFour) {
		this.blockFour = blockFour;
	}

	public Integer getBlockThree() {
		return this.blockThree;
	}

	public void setBlockThree(Integer blockThree) {
		this.blockThree = blockThree;
	}

}
