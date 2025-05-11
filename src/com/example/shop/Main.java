package com.example.shop;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.example.shop.calculation.PaymentValueCalculator;
import com.example.shop.model.Order;
import com.example.shop.model.PaymentMethod;
import com.example.shop.reader.OrderReader;
import com.example.shop.reader.PaymentMethodReader;

public class Main {
	
    private OrderReader orderReader = new OrderReader();
    private PaymentMethodReader paymentMethodReader = new PaymentMethodReader();
    private PaymentValueCalculator calculator = new PaymentValueCalculator();

    public static void main(String[] args) {
        new Main().Run(args);
    }

    public void Run(String[] args) {
        if (args.length < 2) {
            System.out.println("The program call did not receive 2 file paths.");
            return;
        }

        String ordersPath = args[0];
        String paymentMethodsPath = args[1];

        List<Order> orders = orderReader.Read(ordersPath);
        List<PaymentMethod> paymentMethods = paymentMethodReader.Read(paymentMethodsPath);
        
        if(orders.isEmpty() || paymentMethods.isEmpty()) {
            System.out.println("The program was unable to read values into orders or paymentMethods list");
            return;
        }
        
        Map<String, BigDecimal> results = calculator.OptimizePayments(orders, paymentMethods);
        
        for(Map.Entry<String, BigDecimal> result : results.entrySet()) {
            System.out.println(result.getKey() + " " + result.getValue());
        }
    }
    


}
