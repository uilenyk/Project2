package com.revature.models.requests;

import java.math.BigDecimal;
import java.util.List;

import com.revature.models.Images;
import com.revature.models.Tag;

public class ListingPatchRequest {

	private Integer listid;
	private Boolean active;
	private String name;
	private String description;
	private BigDecimal price;
	private List<Tag> tags;
	private List<Images> images;

	public ListingPatchRequest(Integer listid, Boolean active, String name, String description, BigDecimal price,
			List<Tag> tags, List<Images> images) {
		super();
		this.listid = listid;
		this.active = active;
		this.name = name;
		this.description = description;
		this.price = price;
		this.tags = tags;
		this.images = images;
	}

	public ListingPatchRequest() {

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<Images> getImages() {
		return images;
	}

	public void setImages(List<Images> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "ListingPatchRequest [listid=" + listid + ", active=" + active + ", name=" + name + ", description="
				+ description + ", price=" + price + ", tags=" + tags + "]";
	}

}
