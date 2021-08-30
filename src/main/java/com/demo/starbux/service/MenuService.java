package com.demo.starbux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.starbux.domain.Cart;
import com.demo.starbux.domain.Item;
import com.demo.starbux.domain.cart.CartDrink;
import com.demo.starbux.domain.response.Menu;
import com.demo.starbux.repositories.ItemRepo;

@Service
public class MenuService {

	@Autowired
	private ItemRepo itemRepo;
	
	private Cart cart = new Cart();
	public Menu getMenu() {
		Menu menu = new Menu();
		for(Item item:itemRepo.findAll()) {
			if(item.getItemType().equals("drink")) {
				menu.getDrinks().add(item);
			}else {
				menu.getToppings().add(item);
			}
		}
		
		return menu;
	}
	
	public Cart getCart() {
		return cart;
	}

	public String addItem(CartDrink cartDrink) {
		if (cartDrink != null) {
			cart.getCartItems().add(cartDrink);
			return "item is added to cart";
		}
		
		return "No items are added";
	}
}
