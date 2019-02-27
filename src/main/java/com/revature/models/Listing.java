package com.revature.models;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="listings")
public class Listing {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String description;
	private String tags[];
	private String imgRef;
	private BigDecimal price;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="listLife_id")
	private ListLife listLife;
	
	@OneToOne
	private User owner;
}
