package com.revature.models;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

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

	private byte[] tags;

	private Timestamp timeout;

	// bi-directional many-to-one association to MarketPlaceUserListing
	@OneToMany(mappedBy = "listing")
	private List<MarketPlaceUserListing> marketPlaceUserListings;

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

	public byte[] getTags() {
		return this.tags;
	}

	public void setTags(byte[] tags) {
		this.tags = tags;
	}

	public Timestamp getTimeout() {
		return this.timeout;
	}

	public void setTimeout(Timestamp timeout) {
		this.timeout = timeout;
	}

	public List<MarketPlaceUserListing> getMarketPlaceUserListings() {
		return this.marketPlaceUserListings;
	}

	public void setMarketPlaceUserListings(List<MarketPlaceUserListing> marketPlaceUserListings) {
		this.marketPlaceUserListings = marketPlaceUserListings;
	}

	public MarketPlaceUserListing addMarketPlaceUserListing(MarketPlaceUserListing marketPlaceUserListing) {
		getMarketPlaceUserListings().add(marketPlaceUserListing);
		marketPlaceUserListing.setListing(this);
		return marketPlaceUserListing;
	}

	public MarketPlaceUserListing removeMarketPlaceUserListing(MarketPlaceUserListing marketPlaceUserListing) {
		getMarketPlaceUserListings().remove(marketPlaceUserListing);
		marketPlaceUserListing.setListing(null);
		return marketPlaceUserListing;
	}

}
