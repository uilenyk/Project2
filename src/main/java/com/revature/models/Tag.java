package com.revature.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Tag")
@NamedQuery(name = "Tag.findAll", query = "SELECT t FROM Tag t")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "tagid")
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tagid;

	@Column(unique = true)
	private String tagName;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
	@JsonIgnore
//	@JsonBackReference(value = "listings")
	private List<Listing> listings;

	/**
	 * 
	 * @return
	 */
	public Integer getTagid() {
		return tagid;
	}

	/**
	 * 
	 * @param tagid
	 */
	public void setTagid(Integer tagid) {
		this.tagid = tagid;
	}

	/**
	 * 
	 * @return
	 */
	public String getTagName() {
		return tagName;
	}

	/**
	 * 
	 * @param tagName
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	/**
	 * 
	 * @return
	 */
	public List<Listing> getListings() {
		return listings;
	}

	/**
	 * 
	 * @param listings
	 */
	public void setListings(List<Listing> listings) {
		this.listings = listings;
	}

	@Override
	public String toString() {
		return "Tag [tagid=" + tagid + ", tagName=" + tagName + ", listings=" + listings + "]";
	}

}
