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
	@Column(name = "phone_number_id")
	private Integer phoneNumberId;

	@Column(name = "area_code_three")
	private Integer areaCodeThree;

	@Column(name = "block_four")
	private Integer blockFour;

	@Column(name = "block_three")
	private Integer blockThree;

	// bi-directional one-to-one association to MarketPlaceUser
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mpuid")
	private MarketPlaceUser marketPlaceUser;

	public PhoneNumber() {
	}

	public Integer getPhoneNumberId() {
		return this.phoneNumberId;
	}

	public void setPhoneNumberId(Integer phoneNumberId) {
		this.phoneNumberId = phoneNumberId;
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

	public MarketPlaceUser getMarketPlaceUser() {
		return this.marketPlaceUser;
	}

	public void setMarketPlaceUser(MarketPlaceUser marketPlaceUser) {
		this.marketPlaceUser = marketPlaceUser;
	}

}
