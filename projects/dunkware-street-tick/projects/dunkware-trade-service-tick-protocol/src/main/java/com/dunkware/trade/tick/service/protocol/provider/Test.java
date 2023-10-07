package com.dunkware.trade.tick.service.protocol.provider;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.tick.model.provider.TickProviderSpec;

public class Test {

	private static final String username= "dunktrade";
	private static final String password="3goldPigs1!";
	private static final String key= "0be92d7dcaab49ddb477dc0f59904041";
	private static final String server= "activetick1.activetick.com";
	private static final int port=443;

	public static void main(String[] args) {
		TickProviderAddReq req = new  TickProviderAddReq();
		TickProviderSpec spec = new TickProviderSpec();
		spec.setType("ActiveTick");
		spec.getProperties().put("host", server);
		spec.getProperties().put("username", username);
		spec.getProperties().put("port",port);
		spec.getProperties().put("apiKey", key);
		spec.getProperties().put("password", password);
		
		req.setSpec(spec);
		
		try {
			String s = DJson.serializePretty(req);
			System.out.println(s);
		} catch (Exception e) {
			
		}
		
	}
}
