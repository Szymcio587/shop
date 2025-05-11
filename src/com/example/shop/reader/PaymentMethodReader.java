package com.example.shop.reader;

import java.math.BigDecimal;
import java.util.List;

import com.example.shop.model.PaymentMethod;

public final class PaymentMethodReader extends AbstractReader<PaymentMethod> {

	@Override
	protected void ConvertStringToObjects(String json, List<PaymentMethod> paymentMethods) {
        String[] objectStrings = json.split("\\},\\s*\\{");

        for (String obj : objectStrings) {
            obj = obj.trim();
            if (!obj.startsWith("{")) obj = "{" + obj;
            if (!obj.endsWith("}")) obj = obj + "}";

            String id = "";
            String discount = "";
            String limit = "";

            int idIndex = obj.indexOf("\"id\"");
            int discountIndex = obj.indexOf("\"discount\"");
            int limitIndex = obj.indexOf("\"limit\"");

            id = extractValue(obj, idIndex);
            discount = extractValue(obj, discountIndex);
            limit = extractValue(obj, limitIndex);

            paymentMethods.add(new PaymentMethod(id, new BigDecimal(discount), new BigDecimal(limit)));
        }
	}
	
    private static String extractValue(String json, int keyIndex) {
        if (keyIndex < 0) return "";
        int colon = json.indexOf(":", keyIndex);
        int quoteStart = json.indexOf("\"", colon);
        int quoteEnd = json.indexOf("\"", quoteStart + 1);
        return json.substring(quoteStart + 1, quoteEnd);
    }
}
