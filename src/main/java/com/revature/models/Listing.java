package com.revature.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@NamedQuery(name = "Listing.findAll", query = "SELECT l FROM Listing l")
public class Listing implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer listid;

	private Boolean active;

	private String description;

	private String name;

	private BigDecimal price;

//	@Column(name = "tags", columnDefinition = "text[]")
//	@Convert(converter = ListToArrayConverter.class)
//	@Type(type = "string-array")
//	@Column(name = "tags", columnDefinition = "text[]")
	private String tags;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp timeout;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mpu_id")
	@JsonBackReference
	private MarketPlaceUser owner;

	public MarketPlaceUser getOwner() {
		return owner;
	}

	public void setOwner(MarketPlaceUser owner) {
		this.owner = owner;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Listing() {
	}

	public Integer getListid() {
		return this.listid;
	}

	public void setListid(Integer listid) {
		this.listid = listid;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Timestamp getTimeout() {
		return this.timeout;
	}

	public void setTimeout(Timestamp timeout) {
		this.timeout = timeout;
	}

}
