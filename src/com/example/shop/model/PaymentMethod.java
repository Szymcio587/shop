package com.example.shop.model;

public class PaymentMethod {

	private String id;
	private double discount;
	private double limit;
	
	public PaymentMethod() {}
	
	public PaymentMethod(String id, double discount, double limit) {
		super();
		this.id = id;
		this.discount = discount;
		this.limit = limit;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getLimit() {
		return limit;
	}
	public void setLimit(double limit) {
		this.limit = limit;
	}
	
	
}
