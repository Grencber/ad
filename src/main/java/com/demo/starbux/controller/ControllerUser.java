package com.demo.starbux.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.starbux.domain.Cart;
import com.demo.starbux.domain.CombinationToppingItem;
import com.demo.starbux.domain.DrinkCombination;
import com.demo.starbux.domain.Item;
import com.demo.starbux.domain.Order;
import com.demo.starbux.domain.cart.CartDrink;
import com.demo.starbux.domain.response.AmountResponse;
import com.demo.starbux.domain.response.Menu;
import com.demo.starbux.repositories.CombinationToppingItemRepo;
import com.demo.starbux.repositories.DrinkCombinationRepo;
import com.demo.starbux.repositories.ItemRepo;
import com.demo.starbux.repositories.OrderRepo;
import com.demo.starbux.repositories.implementations.ItemRepoInterfaceImpl;
import com.demo.starbux.service.CartService;
import com.demo.starbux.service.MenuService;

@RestController
public class ControllerUser {
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private DrinkCombinationRepo drinkCombinationRepo;

	@Autowired
	private ItemRepoInterfaceImpl itemRepoInterfaceImpl;
	
	@Autowired
	private CombinationToppingItemRepo combinationToppingItemRepo;
	
	@GetMapping("/menu")
	public ResponseEntity<Menu> getMenu() {
		
		return new ResponseEntity<Menu>(menuService.getMenu(), HttpStatus.OK);
	}
	
	@GetMapping("/cart")
	public ResponseEntity<Cart> getCart() {
		
		return new ResponseEntity<Cart>(menuService.getCart(), HttpStatus.OK);
	}
	
	@PostMapping("/addItem")
	public ResponseEntity<String> addItem(@RequestBody CartDrink cartDrink) {
		String result = menuService.addItem(cartDrink);
		if (result == null) {
			return new ResponseEntity<String>("Invalid DrinkName / ToppingName", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	@GetMapping("/finalize")
	public ResponseEntity<AmountResponse> finalizeOrder() {
		
		return new ResponseEntity<AmountResponse>(menuService.finalizeOrder(),HttpStatus.OK);
	}
	
	@GetMapping("/create/{userId}")
	public String createOrder(@PathVariable Integer userId) {
		List<CartDrink> cartDrinkList = menuService.getCart().getCartItems();
		Order order = new Order(userId,menuService.finalizeOrder().getOriginalAmount(),
				menuService.finalizeOrder().getOriginalAmount() - menuService.finalizeOrder().getDiscountedAmount(),
				menuService.finalizeOrder().getDiscountedAmount());
		
		Order savedOrder = orderRepo.save(order);
		for (int i = 0; i < cartDrinkList.size(); i++) {
			DrinkCombination drinkCombination = new DrinkCombination(itemRepoInterfaceImpl.findByName(cartDrinkList.get(i).getDrinkName()), savedOrder.getId());
			DrinkCombination savedDrinkCombination = drinkCombinationRepo.save(drinkCombination);
			
			for(int j = 0; j < cartDrinkList.get(i).getDrinkToppings().size();j++) {
				CombinationToppingItem combinationToppingItem = new CombinationToppingItem(savedDrinkCombination.getId(), itemRepoInterfaceImpl
																			.findByName(cartDrinkList.get(i).getDrinkToppings().get(j).getToppingName()));
				combinationToppingItemRepo.save(combinationToppingItem);
			}
			
		}
		menuService.removeCart();
		return "Success";
		
	}
}
