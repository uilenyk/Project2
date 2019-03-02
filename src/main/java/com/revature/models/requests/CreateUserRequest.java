package com.revature.models.requests;

import com.revature.models.Credential;
import com.revature.models.MarketPlaceUser;

public class CreateUserRequest {

	private MarketPlaceUser user;
	private Credential credential;

	public MarketPlaceUser getUser() {
		return user;
	}

	public void setUser(MarketPlaceUser user) {
		this.user = user;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((credential == null) ? 0 : credential.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		CreateUserRequest other = (CreateUserRequest) obj;
		if (credential == null) {
			if (other.credential != null)
				return false;
		} else if (!credential.equals(other.credential))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	public CreateUserRequest(MarketPlaceUser user, Credential credential) {
		super();
		this.user = user;
		this.credential = credential;
	}

	public CreateUserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CreateUserRequest [user=" + user + ", credential=" + credential + "]";
	}

}
