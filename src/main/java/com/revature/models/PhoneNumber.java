package com.revature.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
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
	public Integer getAreaCodeThree() {
		return this.areaCodeThree;
	}

	/**
	 * 
	 * @param areaCodeThree
	 */
	public void setAreaCodeThree(Integer areaCodeThree) {
		this.areaCodeThree = areaCodeThree;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getBlockFour() {
		return this.blockFour;
	}

	/**
	 * 
	 * @param blockFour
	 */
	public void setBlockFour(Integer blockFour) {
		this.blockFour = blockFour;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getBlockThree() {
		return this.blockThree;
	}

	/**
	 * 
	 * @param blockThree
	 */
	public void setBlockThree(Integer blockThree) {
		this.blockThree = blockThree;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((areaCodeThree == null) ? 0 : areaCodeThree.hashCode());
		result = prime * result + ((blockFour == null) ? 0 : blockFour.hashCode());
		result = prime * result + ((blockThree == null) ? 0 : blockThree.hashCode());
		result = prime * result + id;
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
		PhoneNumber other = (PhoneNumber) obj;
		if (areaCodeThree == null) {
			if (other.areaCodeThree != null)
				return false;
		} else if (!areaCodeThree.equals(other.areaCodeThree))
			return false;
		if (blockFour == null) {
			if (other.blockFour != null)
				return false;
		} else if (!blockFour.equals(other.blockFour))
			return false;
		if (blockThree == null) {
			if (other.blockThree != null)
				return false;
		} else if (!blockThree.equals(other.blockThree))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "PhoneNumber [id=" + id + ", areaCodeThree=" + areaCodeThree + ", blockFour=" + blockFour
				+ ", blockThree=" + blockThree + "]";
	}

}
