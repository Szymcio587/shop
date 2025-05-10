package com.example.shop.model;

import java.util.List;

public class Order {
	private String id;
	private double value;
	private List<String> promotions;
	
	public Order() {}
	
	public Order(String id, double value, List<String> promotions) {
		super();
		this.id = id;
		this.value = value;
		this.promotions = promotions;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public List<String> getPromotions() {
		return promotions;
	}
	public void setPromotions(List<String> promotions) {
		this.promotions = promotions;
	}

	
}
