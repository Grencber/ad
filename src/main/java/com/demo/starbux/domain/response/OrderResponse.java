package com.demo.starbux.domain.response;

import java.util.List;

import com.demo.starbux.domain.Order;

public class OrderResponse {
	List<Order> totalOrders;
	
	public OrderResponse() {
		
	}

	public OrderResponse(List<Order> totalOrders) {
		super();
		this.totalOrders = totalOrders;
	}

	public List<Order> getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(List<Order> totalOrders) {
		this.totalOrders = totalOrders;
	}
	
}
