package com.dunkware.trade.service.stream.server.config;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class Test {

	public static String  jsonString = "{\"name\":\"duncan3\"}";
	public static void main(String[] args) {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(jsonString);
			JSONObject jsonObj = (JSONObject)obj;
			System.out.println(jsonObj.get("name"));
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
	}
}
