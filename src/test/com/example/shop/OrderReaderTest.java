package test.com.example.shop;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.shop.model.Order;
import com.example.shop.reader.OrderReader;

public class OrderReaderTest {

	@Test
    public void OrderReadingTest() {  
        List<Order> orders = new ArrayList<>();
        
        orders = OrderReader.ReadOrders("json/orders.json");
        
        System.out.print(orders.get(0));
        
        if(!orders.isEmpty()) {
        	assertEquals(orders.get(0), new Order("ORDER1", 100, List.of("mZysk")));
        }
        	
    }
	

}
