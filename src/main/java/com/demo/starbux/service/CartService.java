package com.demo.starbux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.starbux.domain.Cart;
import com.demo.starbux.domain.Item;
import com.demo.starbux.domain.cart.CartDrink;
import com.demo.starbux.domain.cart.Topping;
import com.demo.starbux.repositories.ItemRepo;

@Service
public class CartService {
	
	@Autowired
	private ItemRepo itemRepo;

	public double getCartDrinkPrice(CartDrink cartDrink) {
		for (Item currentItem : itemRepo.findAll()) {
			if (currentItem.getItemName().equalsIgnoreCase(cartDrink.getDrinkName())) {
				return currentItem.getItemPrice();
			}
		}
		return 0;
	}

	public void setCartDrinkToppingPrice(CartDrink cartDrink) {
		List<Topping> drinkToppingList = cartDrink.getDrinkToppings();
		if (drinkToppingList == null) {
			return;
		}
		for (int i = 0; i < drinkToppingList.size(); i++) {
			for (Item currentItem : itemRepo.findAll()) {
				if (currentItem.getItemName().equalsIgnoreCase(drinkToppingList.get(i).getToppingName())) {
					cartDrink.getDrinkToppings().get(i).setToppingPrice(currentItem.getItemPrice()); 
				
				}
			}
		}
	
	}

	
}
