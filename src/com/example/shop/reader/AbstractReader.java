package com.example.shop.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractReader<T> {
	
	public List<T> Read(String filename) {
		List<T> objects = new ArrayList<>();
		String json = "";
		
		try {
			json = ReadFileToString(filename);
		}
		catch(IOException exception) {
			exception.printStackTrace();
		}

        ConvertStringToObjects(json, objects);
        
		return objects;
	}

	protected String ReadFileToString(String filename) throws FileNotFoundException, IOException {
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
	
	protected abstract void ConvertStringToObjects(String json, List<T> objects);
	
}
