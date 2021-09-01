package com.demo.starbux.domain.cart;

import java.util.ArrayList;
import java.util.List;

public class CartDrink {
	private List<Topping> drinkToppings;
	private String drinkName;
	private double drinkPrice;
	
	public CartDrink() {
		super();
		drinkToppings = new ArrayList<>();
	}
	
	public CartDrink(List<Topping> drinkToppings, String drinkName) {
		super();
		this.drinkToppings = drinkToppings;
		this.drinkName = drinkName;
		
	}
	
	public CartDrink(List<Topping> drinkToppings, String drinkName, double drinkPrice) {
		super();
		this.drinkToppings = drinkToppings;
		this.drinkName = drinkName;
		this.drinkPrice = drinkPrice;
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

	public double getDrinkPrice() {
		return drinkPrice;
	}

	public void setDrinkPrice(double drinkPrice) {
		this.drinkPrice = drinkPrice;
	}
		
}
