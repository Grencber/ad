package com.demo.starbux.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.starbux.domain.Cart;
import com.demo.starbux.domain.Item;
import com.demo.starbux.domain.cart.CartDrink;
import com.demo.starbux.domain.response.AmountResponse;
import com.demo.starbux.domain.response.Menu;
import com.demo.starbux.service.CartService;
import com.demo.starbux.service.MenuService;

@RestController
public class Controller {
	
	@Autowired
	private MenuService menuService;
	
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
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	@GetMapping("/finalize")
	public ResponseEntity<AmountResponse> finalizeOrder() {
		
		return new ResponseEntity<AmountResponse>(menuService.finalizeOrder(),HttpStatus.OK);
	}
}
