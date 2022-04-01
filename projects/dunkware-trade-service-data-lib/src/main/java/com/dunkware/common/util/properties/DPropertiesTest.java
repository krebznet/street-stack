package com.dunkware.common.util.properties;

import com.dunkware.common.util.json.DJson;

public class DPropertiesTest {

	public static void main(String[] args) {
		DProperties props = DPropertiesBuilder.newBuilder()
		.addProperty("firstName", "Kevin")
		.addProperty("lastName", "Krebs").build();
		
		DProperties props2 = DPropertiesBuilder.newBuilder()
				.addProperty("firstName", "Duncan")
				.addProperty("lastName", "Krebs")
				.importProperties(props, true).build();
				
		
		try {
			String json = DJson.serializePretty(props);
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}

