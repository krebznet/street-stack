package com.dunkware.trade.service.beach.server.gen;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.beach.protocol.trade.pool.BeachPoolAddReq;

public class CreatePoolGen {
	
	public static void main(String[] args) {
		BeachPoolAddReq req = new BeachPoolAddReq();
		req.setAccount("DU222846");
		req.setBroker("DLK Paper");
		req.setName("Test Pool");
		try {
			String ser = DJson.serializePretty(req);
			System.out.println(ser);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
