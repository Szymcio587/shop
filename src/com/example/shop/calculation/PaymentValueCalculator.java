package com.example.shop.calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.shop.model.Order;
import com.example.shop.model.PaymentMethod;

public class PaymentValueCalculator {

	//Traktowałem 'PUNKTY' jako zwykłą nazwę jednego ze sposobów płatności, stąd też zostawiałem nazwy po polsku
	private BigDecimal punktyLimit = BigDecimal.ZERO;
	private static final String PUNKTY = "PUNKTY";
	private PaymentMethod punktyMethod = new PaymentMethod();
	
    public Map<String, BigDecimal> OptimizePayments(List<Order> orders, List<PaymentMethod> methods) {
        Map<String, BigDecimal> result = new HashMap<>();
        orders.sort((a,b) -> b.getValue().compareTo(a.getValue()));
        methods.sort((a,b) -> b.getDiscount().compareTo(a.getDiscount()));
        
        Optional<PaymentMethod> opt = methods.stream().filter(o -> o.getId().equals(PUNKTY)).findFirst();
        
        if(opt.isPresent()) {
        	punktyMethod = opt.get();
        	punktyLimit = punktyMethod.getLimit();
        }

        ApplyCardDiscounts(orders, methods, result);
        
        AllocateRemainingOrders(orders, methods, result);
        
        return result;
    }
    
    private void ApplyCardDiscounts(List<Order> orders, List<PaymentMethod> methods, Map<String, BigDecimal> result) {
        for(PaymentMethod method : methods) {
        	for(Order order : orders) {
        		if(!order.getValue().equals(BigDecimal.ZERO) && !method.getLimit().equals(BigDecimal.ZERO)) {
            		BigDecimal fraction = BigDecimal.ONE.subtract(method.getDiscount().divide(new BigDecimal(100)));
            		BigDecimal value = order.getValue().multiply(fraction).setScale(2, RoundingMode.HALF_UP);
            		if((order.getPromotions().contains(method.getId()) || method.getId().equals(PUNKTY)) &&  method.getLimit().compareTo(value) >= 0) {
            			result.merge(method.getId(), value, BigDecimal::add);
            			method.setLimit(method.getLimit().subtract(value));
            			order.setValue(BigDecimal.ZERO);
            			if(method.getId().equals(PUNKTY)) {
            				punktyLimit = punktyLimit.subtract(value);
            			}
            		}
        		}
        	}
        }
    }
    
    private void AllocateRemainingOrders(List<Order> orders, List<PaymentMethod> methods, Map<String, BigDecimal> result) {
    	for(Order order : orders) {
    		if(!order.getValue().equals(BigDecimal.ZERO)) {
            	BigDecimal punkty10 = order.getValue().divide(BigDecimal.TEN);
            	if(punkty10.compareTo(punktyLimit) <= 0) {
            		result.merge(PUNKTY, punkty10, BigDecimal::add);
            		punktyLimit = punktyLimit.subtract(punkty10);
            		punktyMethod.setLimit(punktyMethod.getLimit().subtract(punkty10));
            		order.setValue(order.getValue().subtract(punkty10.multiply(new BigDecimal(2))));
            	}
        		for(PaymentMethod method : methods) {
        			if(method.getLimit().compareTo(order.getValue()) < 0) {
        				result.merge(method.getId(), method.getLimit(), BigDecimal::add);
        				order.setValue(order.getValue().subtract(method.getLimit()));
        				method.setLimit(BigDecimal.ZERO);
        			}
        			else {
        				result.merge(method.getId(), order.getValue(), BigDecimal::add);
        				method.setLimit(method.getLimit().subtract(order.getValue()));
        				order.setValue(BigDecimal.ZERO);
        				break;
        			}
        		}
    		}
    	}
    }
}
