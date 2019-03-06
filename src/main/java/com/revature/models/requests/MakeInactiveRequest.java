package com.revature.models.requests;

public class MakeInactiveRequest {

	private Integer listid;
	private Boolean active;

	public MakeInactiveRequest(Integer listid, Boolean active) {
		this.listid = listid;
		this.active = active;
	}

	public Integer getListid() {
		return listid;
	}

	public void setListid(Integer listid) {
		this.listid = listid;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "MakeInactiveRequest [listid=" + listid + ", active=" + active + "]";
	}
}
