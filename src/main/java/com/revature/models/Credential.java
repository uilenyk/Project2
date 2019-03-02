package com.revature.models;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQuery(name="Credential.findAll", query="SELECT c FROM Credential c")
public class Credential implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CredentialPK id;

	private String salt;

	//bi-directional one-to-one association to MarketPlaceUser
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mpuid")
	private MarketPlaceUser marketPlaceUser;

	public Credential() {
	}

	public CredentialPK getId() {
		return this.id;
	}

	public void setId(CredentialPK id) {
		this.id = id;
	}

	public String getSalt() {
		return this.salt;
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
