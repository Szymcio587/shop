package test.com.example.shop;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.shop.model.Order;
import com.example.shop.model.PaymentMethod;
import com.example.shop.reader.OrderReader;
import com.example.shop.reader.PaymentMethodReader;

public class PaymentMethodReaderTest {

	@Test
    public void PaymentMethodReadingTest() {  
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        
        PaymentMethodReader paymentMethodReader = new PaymentMethodReader();
        paymentMethods = paymentMethodReader.Read("json/paymentmethods.json");
        
        if(!paymentMethods.isEmpty()) {
        	assertEquals(paymentMethods.get(0), new PaymentMethod("PUNKTY", 15, 100));
        }
        	
    }
}
