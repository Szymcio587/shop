package com.example.shop;

import java.util.List;

import com.example.shop.model.Order;
import com.example.shop.model.PaymentMethod;
import com.example.shop.reader.OrderReader;
import com.example.shop.reader.PaymentMethodReader;

public class Main {
	
    private OrderReader orderReader = new OrderReader();
    private PaymentMethodReader paymentMethodReader = new PaymentMethodReader();

    public static void main(String[] args) {
        new Main().run(args);
    }

    public void run(String[] args) {
        if (args.length < 2) {
            System.out.println("The program call did not receive 2 file paths.");
            return;
        }

        String ordersPath = args[0];
        String paymentMethodsPath = args[1];

        List<Order> orders = orderReader.Read(ordersPath);
        List<PaymentMethod> paymentMethods = paymentMethodReader.Read(paymentMethodsPath);
        
        orders.stream().forEachOrdered(order -> System.out.println(order));
        paymentMethods.stream().forEachOrdered(method -> System.out.println(method));
    }
}
