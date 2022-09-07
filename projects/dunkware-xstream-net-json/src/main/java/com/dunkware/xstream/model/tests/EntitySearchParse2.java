package com.dunkware.xstream.model.tests;

import com.dunkware.common.util.json.DJson;
import com.dunkware.xstream.model.scanner.SessionEntityScanner;
import com.dunkware.xstream.model.search.SessionEntitySearch;
import com.dunkware.xstream.model.search.SessionEntitySearchFilter;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EntitySearchParse2 {
	
	private static String TEST_JSON = " {\n"
			+ "			\"filters\": [\n"
			+ "				{\n"
			+ "					\"type\": \"Value\",\n"
			+ "					\"value\": {\n"
			+ "						\"value\": {\n"
			+ "							\"type\": \"CurrentValue\",\n"
			+ "							\"field\": {\n"
			+ "								\"id\": 2,\n"
			+ "								\"identifier\": \"Last\",\n"
			+ "								\"name\": \"Last\",\n"
			+ "								\"version\": 0.0\n"
			+ "							}\n"
			+ "						},\n"
			+ "						\"condition\": {\n"
			+ "							\"type\": \"Numerical\",\n"
			+ "							\"numeric\": {\n"
			+ "								\"value\": 110,\n"
			+ "								\"operator\": \"GreaterThan\"\n"
			+ "							}\n"
			+ "						}\n"
			+ "					},\n"
			+ "					\"label\": \"(VolCount30sec Value) > 110\",\n"
			+ "					\"enabled\": false,\n"
			+ "					\"name\": \"Filter1\"\n"
			+ "				}\n"
			+ "			]\n"
			+ "		}\n"
			+ "	";
	public static void main(String[] args) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			
			SessionEntitySearchFilter filters = DJson.getObjectMapper().readValue(TEST_JSON,SessionEntitySearchFilter.class);
			System.out.println(filters.toString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}

}
