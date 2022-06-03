package com.dunkware.trade.service.beach.server;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.sdk.tws.model.TwsBrokerType;
import com.dunkware.trade.service.beach.protocol.trade.BeachBrokerAddReq;
import com.dunkware.trade.service.beach.protocol.trade.pool.BeachPoolAddReq;

public class Test {
	
	// Add Trade Pool Req
	public static void main(String[] args) {
		BeachPoolAddReq req = new BeachPoolAddReq();
		req.setBroker("TwsPaper");
		req.setAccount("DU222846");
		req.setName("Test");
		try {
			System.out.println(DJson.serializePretty(req));		
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	
	}
	public static void main2(String[] args) {
		BeachBrokerAddReq req = new BeachBrokerAddReq();
		TwsBrokerType bean = new TwsBrokerType();
		bean.setHost("localhost");
		bean.setIdentifier("TwsPaper");
		bean.setPort(7495);
		req.setBroker(bean);
		try {
			String ser = DJson.serializePretty(req);
			System.out.println(ser);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
