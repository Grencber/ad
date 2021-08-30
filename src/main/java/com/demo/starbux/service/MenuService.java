package com.demo.starbux.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.starbux.domain.Cart;
import com.demo.starbux.domain.Item;
import com.demo.starbux.domain.cart.CartDrink;
import com.demo.starbux.domain.cart.Topping;
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
			boolean allowedItemNameFlag = false;
			
			for (Item current: itemRepo.findAll()) {
				if (current.getItemName().equals(cartDrink.getDrinkName())) {
					allowedItemNameFlag = true;
				}
			}
			
			if (allowedItemNameFlag == false) {
				return "Please choose allowed drink";
			}
			
			if (!cartDrink.getDrinkToppings().isEmpty()) {
				List<Topping> desiredToppings = cartDrink.getDrinkToppings();
				int matchedTopping = 0;
				for (Item current: itemRepo.findAll()) {
					
					if (current.getItemType().equals("topping")) {
						for (Topping topping : desiredToppings) {
							if (topping.getToppingName().equals(current.getItemName())) {
								matchedTopping++;
							}
						}
						if (matchedTopping != desiredToppings.size()) {
							return "Please choose allowed toppings!";
						}
						
					}
				}
				
			}
			
			cart.getCartItems().add(cartDrink);
		}
		
		return "item is added to cart";
	}
}