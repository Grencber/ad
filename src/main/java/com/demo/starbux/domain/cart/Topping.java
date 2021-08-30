package com.demo.starbux.domain.cart;

public class Topping {
	private String toppingName;
	private double toppingPrice;
	
	public Topping(String toppingName, double toppingPrice) {
		super();
		this.toppingName = toppingName;
		this.toppingPrice = toppingPrice;
	}
	public String getToppingName() {
		return toppingName;
	}
	public void setToppingName(String toppingName) {
		this.toppingName = toppingName;
	}
	public double getToppingPrice() {
		return toppingPrice;
	}
	public void setToppingPrice(double toppingPrice) {
		this.toppingPrice = toppingPrice;
	}
	
	
}
