//package com.revature.models;
//
//import java.io.Serializable;
//import java.sql.Timestamp;
//
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.NamedQuery;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "market_place_user_listing")
//@NamedQuery(name = "MarketPlaceUserListing.findAll", query = "SELECT m FROM MarketPlaceUserListing m")
//public class MarketPlaceUserListing implements Serializable {
//	private static final long serialVersionUID = 1L;
//
//	private Timestamp posted;
//
//	// bi-directional many-to-one association to Listing
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "listid")
//	private Listing listing;
//
//	// bi-directional many-to-one association to MarketPlaceUser
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "mpuid")
//	private MarketPlaceUser marketPlaceUser;
//
//	public MarketPlaceUserListing() {
//	}
//
//	public Timestamp getPosted() {
//		return this.posted;
//	}
//
//	public void setPosted(Timestamp posted) {
//		this.posted = posted;
//	}
//
//	public Listing getListing() {
//		return this.listing;
//	}
//
//	public void setListing(Listing listing) {
//		this.listing = listing;
//	}
//
//	public MarketPlaceUser getMarketPlaceUser() {
//		return this.marketPlaceUser;
//	}
//
//	public void setMarketPlaceUser(MarketPlaceUser marketPlaceUser) {
//		this.marketPlaceUser = marketPlaceUser;
//	}
//
//}
