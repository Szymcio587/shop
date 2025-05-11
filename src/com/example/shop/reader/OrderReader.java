package com.example.shop.reader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.shop.model.Order;

public final class OrderReader extends AbstractReader<Order> {
	
	@Override
	protected void ConvertStringToObjects(String json, List<Order> orders) {
        String[] objectStrings = json.split("\\},\\s*\\{");

        for (String obj : objectStrings) {
            obj = obj.trim();
            if (!obj.startsWith("{")) obj = "{" + obj;
            if (!obj.endsWith("}")) obj = obj + "}";

            String id = "";
            String value = "";
            List<String> promotions = new ArrayList<>();

            int idIndex = obj.indexOf("\"id\"");
            int valueIndex = obj.indexOf("\"value\"");
            int promotionsIndex = obj.indexOf("\"promotions\"");

            id = extractValue(obj, idIndex);
            value = extractValue(obj, valueIndex);

            if(promotionsIndex >= 0) {
                String promosRaw = obj.substring(promotionsIndex);
                int startBracket = promosRaw.indexOf("[");
                int endBracket = promosRaw.indexOf("]");
                if (startBracket >= 0 && endBracket > startBracket) {
                    String arrayContent = promosRaw.substring(startBracket + 1, endBracket);
                    String[] promoItems = arrayContent.replace("\"", "").split(",");
                    for (String promo : promoItems) {
                        promotions.add(promo.trim());
                    }
                }
            }

            orders.add(new Order(id, new BigDecimal(value), promotions));
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
