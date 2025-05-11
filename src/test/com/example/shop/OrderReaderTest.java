package test.com.example.shop;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.shop.model.Order;
import com.example.shop.reader.OrderReader;

public final class OrderReaderTest {
	
    private List<Order> orders;
    
    private OrderReader orderReader = new OrderReader();

	@Test
    public void OrderReadingTest() {  
        orders = orderReader.Read("json/orders.json");
        
        if(!orders.isEmpty()) {
        	assertEquals(orders.get(0), new Order("ORDER1", BigDecimal.valueOf(100), List.of("mZysk")));
        }
        	
    }
	

}
