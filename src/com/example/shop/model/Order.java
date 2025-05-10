package com.example.shop.model;

import java.util.List;
import java.util.Objects;

public class Order {
	private String id;
	private double value;
	private List<String> promotions;
	
	public Order() {}
	
	public Order(String id, double value, List<String> promotions) {
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
	
	

	@Override
	public int hashCode() {
		return Objects.hash(id, promotions, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id) && Objects.equals(promotions, other.promotions)
				&& Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", value=" + value + ", promotions=" + promotions + "]";
	}
	
}
