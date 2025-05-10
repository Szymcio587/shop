package com.example.shop.model;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(discount, id, limit);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentMethod other = (PaymentMethod) obj;
		return Double.doubleToLongBits(discount) == Double.doubleToLongBits(other.discount)
				&& Objects.equals(id, other.id)
				&& Double.doubleToLongBits(limit) == Double.doubleToLongBits(other.limit);
	}

	@Override
	public String toString() {
		return "PaymentMethod [id=" + id + ", discount=" + discount + ", limit=" + limit + "]";
	}
	
}
