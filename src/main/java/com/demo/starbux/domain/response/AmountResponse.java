package com.demo.starbux.domain.response;

public class AmountResponse {

	private double originalAmount;
	private double discountedAmount;
	
	public AmountResponse() {

	}
	
	public AmountResponse(double originalAmount, double discountedAmount) {
		super();
		this.originalAmount = originalAmount;
		this.discountedAmount = discountedAmount;
	}

	public double getOriginalAmount() {
		return originalAmount;
	}

	public void setOriginalAmount(double originalAmount) {
		this.originalAmount = originalAmount;
	}

	public double getDiscountedAmount() {
		return discountedAmount;
	}

	public void setDiscountedAmount(double discountedAmount) {
		this.discountedAmount = discountedAmount;
	}
	
}
