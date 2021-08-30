package com.demo.starbux.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.starbux.domain.CombinationToppingItem;
import com.demo.starbux.domain.DrinkCombination;
import com.demo.starbux.domain.Order;
import com.demo.starbux.domain.cart.CartDrink;
import com.demo.starbux.repositories.CombinationToppingItemRepo;
import com.demo.starbux.repositories.DrinkCombinationRepo;
import com.demo.starbux.repositories.ItemRepo;
import com.demo.starbux.repositories.OrderRepo;
import com.demo.starbux.repositories.implementations.ItemRepoInterfaceImpl;
import com.demo.starbux.service.MenuService;

@RestController
@RequestMapping("/admin")
public class ControllerAdmin {
	


	
}
