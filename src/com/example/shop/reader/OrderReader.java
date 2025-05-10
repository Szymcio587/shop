package com.example.shop.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.shop.model.Order;

public class OrderReader {

	public static List<Order> ReadOrders(String filename) {
		List<Order> orders = new ArrayList<>();
		String json = "";
		
		try {
			json = OrderReader.ReadFileToString(filename);
		}
		catch(IOException exception) {
			exception.printStackTrace();
		}

        ConvertStringToOrders(json, orders);
        
		return orders;
	}
	
	private static String ReadFileToString(String filename) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line.trim());
        }
        reader.close();

        String json = sb.toString();

        return json.substring(1, json.length() - 1);
	}
	
	private static void ConvertStringToOrders(String json, List<Order> orders) {
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

            orders.add(new Order(id, Double.valueOf(value), promotions));
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
