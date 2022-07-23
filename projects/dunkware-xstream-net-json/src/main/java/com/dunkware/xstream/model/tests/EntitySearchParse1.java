package com.dunkware.xstream.model.tests;

import com.dunkware.xstream.model.scanner.SessionEntityScanner;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EntitySearchParse1 {
	
	private static String filter1 = "{\n"
			+ "    \"name\": \"PL\",\n"
			+ "    \"search\": {\n"
			+ "        \"type\": \"Filter\",\n"
			+ "        \"name\": \"PL\",\n"
			+ "        \"filterSearch\": {\n"
			+ "            \"filters\": [\n"
			+ "                {\n"
			+ "                    \"type\": \"Value\",\n"
			+ "                    \"label\": \"(VolCount30sec Value) > 5\",\n"
			+ "                    \"name\": \"RR\",\n"
			+ "                    \"enabled\": null,\n"
			+ "                    \"value\": {\n"
			+ "                        \"value\": {\n"
			+ "                            \"type\": \"CurrentValue\",\n"
			+ "                            \"field\": {\n"
			+ "                                \"id\": 1,\n"
			+ "                                \"identifier\": \"Entity\",\n"
			+ "                                \"name\": \"Entity\",\n"
			+ "                                \"category\": null,\n"
			+ "                                \"version\": 0\n"
			+ "                            }\n"
			+ "                        },\n"
			+ "                        \"condition\": {\n"
			+ "                            \"type\": \"Numerical\",\n"
			+ "                            \"numeric\": {\n"
			+ "                                \"operator\": \"GreaterThan\",\n"
			+ "                                \"value\": 5\n"
			+ "                            }\n"
			+ "                        }\n"
			+ "                    }\n"
			+ "                }\n"
			+ "            ]\n"
			+ "        }\n"
			+ "    },\n"
			+ "    \"streamIdentifier\": \"us_equity\",\n"
			+ "    \"id\": null\n"
			+ "}";
	
	public static void main(String[] args) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			SessionEntityScanner test = mapper.readValue(filter1, SessionEntityScanner.class); 
			System.out.println(test.getSearch().getType().toString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}

}
