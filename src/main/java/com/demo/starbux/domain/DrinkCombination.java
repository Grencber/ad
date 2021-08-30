package com.demo.starbux.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "drink_combination")
public class DrinkCombination {
	
	@Id
	@GeneratedValue
	private Integer id;
	@Nullable
	private Integer drinkItemId;
	@Nullable
	private Integer orderId;

	public DrinkCombination() {

	}
	
	public DrinkCombination(Integer drinkItemId, Integer orderId) {

		this.drinkItemId = drinkItemId;
		this.orderId = orderId;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDrinkItemId() {
		return drinkItemId;
	}
	public void setDrinkItemId(Integer drinkItemId) {
		this.drinkItemId = drinkItemId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	
}
