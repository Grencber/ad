package com.demo.starbux.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue
	private Integer id;
	@Nullable
	private double orderGross;
	@Nullable
	private double orderDiscount;
	@Nullable
	private double orderNet;
	
	public Order() {
	}
	
	public Order(double orderGross, double orderDiscount, double orderNet) {
		super();
		this.orderGross = orderGross;
		this.orderDiscount = orderDiscount;
		this.orderNet = orderNet;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getOrderGross() {
		return orderGross;
	}
	public void setOrderGross(double orderGross) {
		this.orderGross = orderGross;
	}
	public double getOrderDiscount() {
		return orderDiscount;
	}
	public void setOrderDiscount(double orderDiscount) {
		this.orderDiscount = orderDiscount;
	}
	public double getOrderNet() {
		return orderNet;
	}
	public void setOrderNet(double orderNet) {
		this.orderNet = orderNet;
	}
	
	
	
}
