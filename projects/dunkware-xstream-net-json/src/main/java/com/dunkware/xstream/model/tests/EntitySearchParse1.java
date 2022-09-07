package com.dunkware.xstream.model.tests;

import com.dunkware.common.util.json.DJson;
import com.dunkware.xstream.model.scanner.SessionEntityScanner;
import com.dunkware.xstream.model.search.SessionEntitySearch;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EntitySearchParse1 {
	
	private static String TEST_JSON = "{\"search\":{\"type\":\"Filter\",\"filterSearch\":{\"filters\":[{\"type\":\"Value\",\"value\":{\"value\":{\"type\":\"CurrentValue\",\"field\":{\"id\":2,\"identifier\":\"Last\",\"name\":\"Last\",\"version\":0.0}},\"condition\":{\"type\":\"Numerical\",\"numeric\":{\"value\":110,\"operator\":\"GreaterThan\"}}},\"label\":\"(VolCount30sec Value) > 110\",\"enabled\":false,\"name\":\"Filter1\"}]},\"name\":\"Search 1\"},\"name\":\"Search 1\",\"streamIdentifier\":\"us_equity\"}";
	public static void main(String[] args) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			
			SessionEntityScanner search = DJson.getObjectMapper().readValue(TEST_JSON,SessionEntityScanner.class);
			System.out.println(search.toString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}

}
