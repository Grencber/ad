package com.demo.starbux.domain.cart;

import java.util.ArrayList;
import java.util.List;

public class CartDrink {
	private List<Topping> drinkToppings;
	private String drinkName;
		
	public CartDrink(List<Topping> drinkToppings, String drinkName) {
		super();
		this.drinkToppings = drinkToppings;
		this.drinkName = drinkName;
	}
		
	public List<Topping> getDrinkToppings() {
		return drinkToppings;
	}

	public void setDrinkToppings(List<Topping> drinkToppings) {
		this.drinkToppings = drinkToppings;
	}

	public String getDrinkName() {
		return drinkName;
	}
	public void setDrinkName(String drinkName) {
		this.drinkName = drinkName;
	}
	public CartDrink() {
		super();
		drinkToppings = new ArrayList<>();
	}
}
