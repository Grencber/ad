package com.demo.starbux.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.starbux.domain.Item;
import com.demo.starbux.domain.Order;
import com.demo.starbux.domain.response.OrderResponse;
import com.demo.starbux.repositories.ItemRepo;
import com.demo.starbux.repositories.OrderRepo;

@RestController
@RequestMapping("/admin")
public class ControllerAdmin {
	
	@Autowired
	private ItemRepo itemRepo;
	
	@Autowired
	private OrderRepo orderRepo;
	
	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody Item requestedItem) {
		Item itemToSave = new Item(requestedItem.getItemName(), requestedItem.getItemType(), requestedItem.getItemPrice());
		itemRepo.save(itemToSave);
		return ResponseEntity.status(HttpStatus.OK).body("New item is added to Item List");
	}

	@PutMapping("/update")
//	@PatchMapping("/patch")
	public ResponseEntity<Item> update(@RequestBody Item updateItem) {
		Item itemToReplace = new Item(updateItem.getItemName(), updateItem.getItemType(), updateItem.getItemPrice());
		Item updatedItem = itemRepo.save(itemToReplace);
		ResponseEntity.status(HttpStatus.OK).build();
		return ResponseEntity.ok(updatedItem);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		Optional<Item> itemToBeDeleted = itemRepo.findById(id);
		if (!itemToBeDeleted.isEmpty() & !itemToBeDeleted.equals(null)) {
			itemRepo.deleteById(id);
			return ResponseEntity.ok("Item is deleted");
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/reports")
	public ResponseEntity<OrderResponse> getReports() {
		Iterable<Order> iterablelist = orderRepo.findAll();
		ArrayList<Order> orderList = new ArrayList<>();
		for (Order order : iterablelist) {
			orderList.add(order);
		}
		OrderResponse orderResponse = new OrderResponse(orderList);
		return ResponseEntity.ok(orderResponse);
	}
}
