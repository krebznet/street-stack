package com.dunkware.common.util.helpers;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DJsonHelper {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public static Map<String,Object> parseJsonToMap(String element) throws Exception  { 
		TypeReference<HashMap<String,Object>> typeRef = new   TypeReference<HashMap<String,Object>>() {
		};
		Map<String,Object> map = objectMapper.readValue(element,typeRef);
		return map;
	}
	
	public void helloMom() { 
		System.out.println("push");
	}
}
