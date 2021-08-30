package com.demo.starbux.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.demo.starbux.domain.Item;
import com.demo.starbux.repositories.ItemRepo;

@Component
public class InitialItemGeneratorService implements CommandLineRunner{

	@Autowired
	private ItemRepo itemRepo;
	
	private static final Logger log = LoggerFactory
							.getLogger(InitialItemGeneratorService.class);
	
	@Override
	public void run(String... args) throws Exception {
		Item drinkItem1 = new Item("Black Coffee", "drink", 4);
		itemRepo.save(drinkItem1);
		Item drinkItem2 = new Item("Latte", "drink", 5);
		itemRepo.save(drinkItem2);
		Item drinkItem3 = new Item("Mocha", "drink", 6);
		itemRepo.save(drinkItem3);
		Item drinkItem4 = new Item("Tea", "drink", 3);
		itemRepo.save(drinkItem4);
		
		Item toppingItem1 = new Item("Milk", "topping", 2);
		itemRepo.save(toppingItem1);
		Item toppingItem2 = new Item("Hazelnut syrup", "topping", 3);
		itemRepo.save(toppingItem2);
		Item toppingItem3 = new Item("Chocolate sauce", "topping", 5);
		itemRepo.save(toppingItem3);
		Item toppingItem4 = new Item("Lemon", "topping", 2);
		itemRepo.save(toppingItem4);
	}

}
