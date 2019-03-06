package com.revature.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.revature.abstraction.Timewatch;

@Entity
@Table(name = "Listing")
@NamedQuery(name = "Listing.findAll", query = "SELECT l FROM Listing l")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "listid")
public class Listing implements Timewatch, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer listid;

	private Boolean active;

	private String description;

	private String name;

	@Positive
	private BigDecimal price;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Tag.class)
	@JoinTable(name = "listing_tag", joinColumns = @JoinColumn(name = "listid"), inverseJoinColumns = @JoinColumn(name = "tagid"))
	@Fetch(FetchMode.SUBSELECT)
//	@JsonManagedReference(value="tags")
	private List<Tag> tags;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Timestamp timeout;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mpu_id")
	// @JsonManagedReference(value = "owner")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private MarketPlaceUser owner;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "listing_id")
	@JsonManagedReference(value = "images")
	private List<Images> images;

	public Listing() {
	}

	@Override
	public void setLife(int days, boolean active) {
		LocalDateTime nowPlusDays = LocalDateTime.now().plusDays(days);
		Timestamp timeout = Timestamp.valueOf(nowPlusDays);
		this.setTimeout(timeout);
		this.setActive(active);
	}

	@Override
	public void resetLife(LocalDateTime targetDateTime) {
		LocalDateTime timeoutDate = this.timeout.toLocalDateTime();
		int timeoutDay = timeoutDate.getDayOfMonth();
		int targetDay = targetDateTime.getDayOfMonth();
		int timeDiff = (timeoutDay - targetDay);
		if (timeDiff < 7) {
			this.setLife(10, true);
		}
	}

	public List<Images> getImages() {
		return images;
	}

	public void setImages(List<Images> images) {
		this.images = images;
	}

	public MarketPlaceUser getOwner() {
		return owner;
	}

	public void setOwner(MarketPlaceUser owner) {
		this.owner = owner;
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

	public List<Tag> getTags() {
		return this.tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Timestamp getTimeout() {
		return this.timeout;
	}

	public void setTimeout(Timestamp timeout) {
		this.timeout = timeout;
	}

	@Override
	public String toString() {
		return "Listing [listid=" + listid + ", active=" + active + ", description=" + description + ", name=" + name
				+ ", price=" + price + ", tags=" + tags + ", timeout=" + timeout + ", owner=" + owner + "]";
	}

}
