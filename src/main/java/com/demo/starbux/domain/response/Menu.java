package com.demo.starbux.domain.response;

import java.util.ArrayList;
import java.util.List;

import com.demo.starbux.domain.Item;

public class Menu {
	
	private List<Item> drinks;
	private List<Item> toppings;
	private String currency;
	
	public Menu() {
		super();
		drinks = new ArrayList<>();
		toppings = new ArrayList<>();
		currency = "Euro";
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public List<Item> getDrinks() {
		return drinks;
	}
	public void setDrinks(List<Item> drinks) {
		this.drinks = drinks;
	}
	public List<Item> getToppings() {
		return toppings;
	}
	public void setToppings(List<Item> toppings) {
		this.toppings = toppings;
	}
	
	
}
