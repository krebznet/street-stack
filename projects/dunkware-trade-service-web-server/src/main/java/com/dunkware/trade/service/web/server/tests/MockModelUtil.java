package com.dunkware.trade.service.web.server.tests;

import com.dunkware.net.util.JsonHelper;

public class MockModelUtil {
	
	public static String modelToJson(MockModel model) throws Exception { 
		try {
			String json = JsonHelper.serialize(model);
			return json;
		} catch (Exception e) {
			throw e;
		}
		
	}
	

}
