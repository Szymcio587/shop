package test.com.example.shop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.example.shop.calculation.PaymentValueCalculator;
import com.example.shop.model.Order;
import com.example.shop.model.PaymentMethod;

public class PaymentValueCalculatorTest {
	
    private List<Order> orders = Arrays.asList(
    		new Order("ORDER1", BigDecimal.valueOf(100), List.of("mZysk")),
    		new Order("ORDER2", BigDecimal.valueOf(200), List.of("BosBankrut")),
    		new Order("ORDER3", BigDecimal.valueOf(150), List.of("mZysk", "BosBankrut")),
    		new Order("ORDER4", BigDecimal.valueOf(50), List.of())
    		);
    
    private List<PaymentMethod> paymentMethods = Arrays.asList(
    		new PaymentMethod("PUNKTY", BigDecimal.valueOf(15), BigDecimal.valueOf(100)),
    		new PaymentMethod("mZysk", BigDecimal.valueOf(10), BigDecimal.valueOf(180)),
    		new PaymentMethod("BosBankrut", BigDecimal.valueOf(5), BigDecimal.valueOf(200))
    		);
    
    private PaymentValueCalculator calculator = new PaymentValueCalculator();

	@Test
    public void OrderReadingTest() {  
        Map<String, BigDecimal> results = calculator.OptimizePayments(orders, paymentMethods);
        
        if(!results.isEmpty()) {
        	assertTrue(results.get("mZysk").compareTo(BigDecimal.valueOf(165)) == 0);
        	assertTrue(results.get("BosBankrut").compareTo(BigDecimal.valueOf(190)) == 0);
        	assertTrue(results.get("PUNKTY").compareTo(BigDecimal.valueOf(100)) == 0);
        }
        	
    }
}
