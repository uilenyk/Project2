package com.revature.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "credit_card")
@NamedQuery(name = "CreditCard.findAll", query = "SELECT c FROM CreditCard c")
public class CreditCard implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal balance;

	// bi-directional one-to-one association to MarketPlaceUser
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mpuid")
	private MarketPlaceUser marketPlaceUser;

	public CreditCard() {
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public MarketPlaceUser getMarketPlaceUser() {
		return this.marketPlaceUser;
	}

	public void setMarketPlaceUser(MarketPlaceUser marketPlaceUser) {
		this.marketPlaceUser = marketPlaceUser;
	}

}
