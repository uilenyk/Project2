package com.revature.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "credit_card")
@NamedQuery(name = "CreditCard.findAll", query = "SELECT c FROM CreditCard c")
public class CreditCard implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Positive
	private BigDecimal balance;

	public CreditCard() {
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "CreditCard [id=" + id + ", balance=" + balance + "]";
	}

}
