package com.dunkware.xstream.model.tests;

import com.dunkware.xstream.model.search.SessionEntitySearch;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EntitySearchParse1 {
	
	private static String filter1 = "{\n"
			+ "        \"type\": 0,\n"
			+ "        \"name\": \"allFilters\",\n"
			+ "        \"filterSearch\": [\n"
			+ "          {\n"
			+ "            \"type\": 0,\n"
			+ "            \"name\": \"Simple Value Filter\",\n"
			+ "            \"label\": \"(VolCount30sec Value) > 50\",\n"
			+ "            \"filterSearch\": {\n"
			+ "              \"type\": \"Value\",\n"
			+ "              \"label\": \"(VolCount30sec Value) > 50\",\n"
			+ "              \"enabled\": null,\n"
			+ "              \"value\": {\n"
			+ "                \"value\": {\n"
			+ "                  \"type\": \"CurrentFieldValue\",\n"
			+ "                  \"field\": {\n"
			+ "                    \"id\": 2,\n"
			+ "                    \"identifier\": \"Last\",\n"
			+ "                    \"name\": \"Last\",\n"
			+ "                    \"category\": null,\n"
			+ "                    \"version\": 0\n"
			+ "                  }\n"
			+ "                },\n"
			+ "                \"condition\": {\n"
			+ "                  \"type\": \"Numeric\",\n"
			+ "                  \"numeric\": {\n"
			+ "                    \"operator\": \"GreaterThan\",\n"
			+ "                    \"value\": 50\n"
			+ "                  }\n"
			+ "                }\n"
			+ "              }\n"
			+ "            }\n"
			+ "          }\n"
			+ "        ]\n"
			+ "      }\n"
			+ "          ";
	
	public static void main(String[] args) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			SessionEntitySearch test = mapper.readValue(filter1, SessionEntitySearch.class); 
			System.out.println(test.getType().toString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}

}
