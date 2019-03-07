package com.revature.models.reponse;

import com.revature.models.CreditCard;
import com.revature.models.Listing;

public class BuyerReceipt {

	private String buyerName;
	private String sellerName;
	private Listing boughtItem;

	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public Listing getBoughtItem() {
		return boughtItem;
	}
	public void setBoughtItem(Listing boughtItem) {
		this.boughtItem = boughtItem;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boughtItem == null) ? 0 : boughtItem.hashCode());
		result = prime * result + ((buyerName == null) ? 0 : buyerName.hashCode());
		result = prime * result + ((sellerName == null) ? 0 : sellerName.hashCode());
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
		BuyerReceipt other = (BuyerReceipt) obj;
		if (boughtItem == null) {
			if (other.boughtItem != null)
				return false;
		} else if (!boughtItem.equals(other.boughtItem))
			return false;
		if (buyerName == null) {
			if (other.buyerName != null)
				return false;
		} else if (!buyerName.equals(other.buyerName))
			return false;
		if (sellerName == null) {
			if (other.sellerName != null)
				return false;
		} else if (!sellerName.equals(other.sellerName))
			return false;
		return true;
	}
	public BuyerReceipt(String buyerName, String sellerName, Listing boughtItem) {
		super();
		this.buyerName = buyerName;
		this.sellerName = sellerName;
		this.boughtItem = boughtItem;
	}
	public BuyerReceipt() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "buyerName=" + buyerName + ", sellerName="
				+ sellerName + ", boughtItem=" + boughtItem + "]";
	}
	
	
}
