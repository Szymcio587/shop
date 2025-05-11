package test.com.example.shop;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.shop.model.PaymentMethod;
import com.example.shop.reader.PaymentMethodReader;

public final class PaymentMethodReaderTest {
	
    private List<PaymentMethod> paymentMethods;
    
    private PaymentMethodReader paymentMethodReader = new PaymentMethodReader();

	@Test
    public void PaymentMethodReadingTest() {  
        paymentMethods = paymentMethodReader.Read("json/paymentmethods.json");
        
        if(!paymentMethods.isEmpty()) {
        	assertEquals(paymentMethods.get(0), new PaymentMethod("PUNKTY", BigDecimal.valueOf(15), BigDecimal.valueOf(100)));
        }
        	
    }
}
