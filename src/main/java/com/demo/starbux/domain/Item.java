package com.demo.starbux.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "Item")
public class Item {
	
	@Id
	@GeneratedValue
	private Integer id;
	@Nullable
	private String itemName;
	@Nullable
	private String itemType;
	@Nullable
	private double itemPrice;
	
	public Item() {
	
	}
	
	public Item(String itemName, String itemType, double itemPrice) {
		super();
		this.itemName = itemName;
		this.itemType = itemType;
		this.itemPrice = itemPrice;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	
}
