package com.dunkware.xstream.model.tests;

import com.dunkware.xstream.model.search.SessionEntitySearch;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EntitySearchParse1 {
	
	private static String filter1 = "  {\n"
			+ "  \"type\": \"Filter\",\n"
			+ "  \"name\": \"allFilters\",\n"
			+ "  \"filterSearch\": {\n"
			+ "    \"filters\": [\n"
			+ "      {\n"
			+ "        \"type\": \"ValueCompare\",\n"
			+ "        \"label\": \"(VolCount30sec ValueCompare) PercentChange (VolCount30sec ValueCompare) > 100\",\n"
			+ "        \"enabled\": null,\n"
			+ "        \"valueCompare\": {\n"
			+ "          \"value1\": {\n"
			+ "            \"type\": \"CurrentValue\",\n"
			+ "            \"field\": {\n"
			+ "              \"id\": 230,\n"
			+ "              \"identifier\": \"VolCount30sec\",\n"
			+ "              \"name\": \"VolCount30sec\",\n"
			+ "              \"category\": null,\n"
			+ "              \"version\": 0\n"
			+ "            }\n"
			+ "          },\n"
			+ "          \"value2\": {\n"
			+ "            \"type\": \"CurrentValue\",\n"
			+ "            \"field\": null\n"
			+ "          },\n"
			+ "          \"function\": {\n"
			+ "            \"function\": \"PercentChange\"\n"
			+ "          },\n"
			+ "          \"condition\": {\n"
			+ "            \"type\": \"Numerical\",\n"
			+ "            \"numeric\": {\n"
			+ "              \"operator\": \"GreaterThan\",\n"
			+ "              \"value\": 100\n"
			+ "            }\n"
			+ "          }\n"
			+ "        }\n"
			+ "      }\n"
			+ "    ]\n"
			+ "  }\n"
			+ "}";
	
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
