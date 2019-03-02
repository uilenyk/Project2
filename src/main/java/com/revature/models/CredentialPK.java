package com.revature.models;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CredentialPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String email;

	private String password;

	public CredentialPK() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CredentialPK)) {
			return false;
		}
		CredentialPK castOther = (CredentialPK) other;
		return this.email.equals(castOther.email) && this.password.equals(castOther.password);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.email.hashCode();
		hash = hash * prime + this.password.hashCode();
		return hash;
	}
}
