package com.dunkware.net.core.autosearch;

import com.dunkware.common.util.time.DunkTime;
import com.dunkware.net.core.runtime.util.JsonHelper;

public class JsonSearchResultsTest {
	
	public static void main(String[] args) {
		
	JsonSearchResults results = JsonSearchResultsBuilder.newBuilder().addCategory("test", "test")
			.addElement(1,"GOOG", "GOOGLE").build().build();
	
		try {
			String json = JsonHelper.serializePretty(results);
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
	}

}
