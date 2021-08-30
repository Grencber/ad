package com.demo.starbux.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.starbux.domain.Cart;
import com.demo.starbux.domain.Item;
import com.demo.starbux.domain.cart.CartDrink;
import com.demo.starbux.domain.cart.Topping;
import com.demo.starbux.domain.response.AmountResponse;
import com.demo.starbux.domain.response.Menu;
import com.demo.starbux.repositories.ItemRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class MenuService {

	@Autowired
	private ItemRepo itemRepo;
	
	@Autowired
	private CartService cartService;
	
	private Cart cart = new Cart();
	private static ObjectMapper objectMapper = new ObjectMapper();
	private static ObjectNode objectNode1 = objectMapper.createObjectNode();
	private static ObjectNode objectNode2 = objectMapper.createObjectNode();
	
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
				if (current.getItemName().equalsIgnoreCase(cartDrink.getDrinkName())) {
					allowedItemNameFlag = true;
					cartDrink.setDrinkPrice(cartService.getCartDrinkPrice(cartDrink));
				}
			}
			
			if (allowedItemNameFlag == false) {
				return "Please choose allowed drink";
			}
			
			if (!cartDrink.getDrinkToppings().isEmpty()) {
				List<Topping> desiredToppings = cartDrink.getDrinkToppings();
				int matchedTopping = 0;
				for (Item current: itemRepo.findAll()) {
					
					if (current.getItemType().equalsIgnoreCase("topping")) {
						for (Topping topping : desiredToppings) {
							if (topping.getToppingName().equalsIgnoreCase(current.getItemName())) {
								matchedTopping++;
							}
						}
						
						
					}
				}
				if (matchedTopping != desiredToppings.size()) {
					return "Please choose allowed toppings!";
				}
				
			}
			cartService.setCartDrinkToppingPrice(cartDrink);
			cart.getCartItems().add(cartDrink);
			
		}
		
		return "item is added to cart";
	}

	public AmountResponse finalizeOrder() {
//		boolean moreThan12PromotionFlag = false;
//		boolean equalsOrMoreThan3DrinksPromotionFlag = false;
		AmountResponse amountResponse = new AmountResponse();
		isCartEligibleForMoreThan12Promotion(cart);
		isCartEligibleFor3OrMoreThan3DrinksPromotion(cart);
		if (objectNode1.get("eligiblity").booleanValue() & objectNode2.get("eligiblity").booleanValue()) {
			if (objectNode1.get("discounted amount").doubleValue() > objectNode2.get("discounted amount").doubleValue()) {
				amountResponse.setDiscountedAmount(objectNode2.get("discounted amount").doubleValue());
				amountResponse.setOriginalAmount(objectNode2.get("original amount").doubleValue());
			} else {
				amountResponse.setDiscountedAmount(objectNode1.get("discounted amount").doubleValue());
				amountResponse.setOriginalAmount(objectNode1.get("original amount").doubleValue());
			}
		} else if (objectNode1.get("eligiblity").booleanValue()) {
			amountResponse.setDiscountedAmount(objectNode1.get("discounted amount").doubleValue());
			amountResponse.setOriginalAmount(objectNode1.get("original amount").doubleValue());
		} else if (objectNode2.get("eligiblity").booleanValue()) {
			amountResponse.setDiscountedAmount(objectNode2.get("discounted amount").doubleValue());
			amountResponse.setOriginalAmount(objectNode2.get("original amount").doubleValue());
		} else {
			amountResponse.setDiscountedAmount(objectNode1.get("original amount").doubleValue());
			amountResponse.setOriginalAmount(objectNode1.get("original amount").doubleValue());
		}
		return amountResponse;
	}

	private void isCartEligibleForMoreThan12Promotion(Cart cart) {
		boolean promotionEligiblityFlag1 = false;
		double sum = 0;
		for (CartDrink currentDrink : cart.getCartItems()) {
			sum += currentDrink.getDrinkPrice();
			for (int i = 0; i < currentDrink.getDrinkToppings().size(); i++) {
				sum += currentDrink.getDrinkToppings().get(i).getToppingPrice();
			}
		}
		
		if (sum > 12) {
			promotionEligiblityFlag1 = true;
		}
		
		objectNode1.put("eligiblity", promotionEligiblityFlag1);
		objectNode1.put("original amount", sum);
		objectNode1.put("discounted amount", sum * 0.75);
	}
	
	private void isCartEligibleFor3OrMoreThan3DrinksPromotion(Cart cart) {
		boolean promotionEligiblityFlag2 = false;
		double min = 0;
		Double originalAmount = (double) 0;
		Double sum = (double) 0;
		if (cart.getCartItems().size() >= 3) {
			promotionEligiblityFlag2 = true;
			List<Double> drinkSumsList = new ArrayList<>();
			for (CartDrink currentDrink : cart.getCartItems()) {
				double currentDrinkWithToppingsSum = 0;
				currentDrinkWithToppingsSum += currentDrink.getDrinkPrice();
				for (int i = 0; i < currentDrink.getDrinkToppings().size(); i++) {
					currentDrinkWithToppingsSum += currentDrink.getDrinkToppings().get(i).getToppingPrice();
				}
				drinkSumsList.add(currentDrinkWithToppingsSum);
			}
			originalAmount = drinkSumsList.stream().mapToDouble(Double::doubleValue).sum();
			min = Collections.min(drinkSumsList);
			drinkSumsList.remove(min);
			sum = drinkSumsList.stream().mapToDouble(Double::doubleValue).sum();
		}
		
		objectNode2.put("eligiblity", promotionEligiblityFlag2);
		objectNode2.put("original amount", originalAmount);
		objectNode2.put("discounted amount", sum);
	}
}
