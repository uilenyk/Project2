package com.revature.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Tag.findAll", query = "SELECT t FROM Tag t")
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tagid;

	private String tagname;

	public Tag() {
	}

	public Integer getTagid() {
		return this.tagid;
	}

	public void setTagid(Integer tagid) {
		this.tagid = tagid;
	}

	public String getTagname() {
		return this.tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

}
