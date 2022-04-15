package com.dunkware.trade.service.stream.server.autosearch.engine.json;

import com.dunkware.net.core.runtime.util.JsonHelper;

public class GenerateJson {
	
	public static void main(String[] args) {
		try {
			JsonSearchResults results = JsonSearchResultsBuilder
					.newBuilder().addCategory("Instruements", "Instruments")
					.addElement(1, "GOOG", "Google").addElement(2, "APPL", "Apple").build().build();
			String json = JsonHelper.serializePretty(results);
			System.out.println(json);
					
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	
	}

}
