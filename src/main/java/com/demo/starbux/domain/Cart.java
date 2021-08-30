package com.demo.starbux.domain;

import java.util.ArrayList;
import java.util.List;

import com.demo.starbux.domain.cart.CartDrink;

public class Cart {
	private List<CartDrink> cartItems;

	public Cart() {
		super();
		cartItems = new ArrayList<>();
	}

	public List<CartDrink> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartDrink> cartItems) {
		this.cartItems = cartItems;
	}
	
	
}
