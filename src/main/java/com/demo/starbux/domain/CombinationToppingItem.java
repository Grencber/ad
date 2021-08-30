package com.demo.starbux.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "combination_topping_item")
public class CombinationToppingItem {

	@Id
	@GeneratedValue
	private Integer id;
	@Nullable
	private Integer drinkCombinationId;
	@Nullable
	private Integer toppingItemId;
	
	public CombinationToppingItem() {
	
	}
	
	public CombinationToppingItem(Integer drinkCombinationId, Integer toppingItemId) {
		super();
		this.drinkCombinationId = drinkCombinationId;
		this.toppingItemId = toppingItemId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDrinkCombinationId() {
		return drinkCombinationId;
	}
	public void setDrinkCombinationId(Integer drinkCombinationId) {
		this.drinkCombinationId = drinkCombinationId;
	}
	public Integer getToppingItemId() {
		return toppingItemId;
	}
	public void setToppingItemId(Integer toppingItemId) {
		this.toppingItemId = toppingItemId;
	}
	
	
}
