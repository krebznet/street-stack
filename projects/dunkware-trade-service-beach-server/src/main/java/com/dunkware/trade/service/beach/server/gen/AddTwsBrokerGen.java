
package com.dunkware.trade.service.beach.server.gen;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.broker.tws.TwsBrokerType;
import com.dunkware.trade.service.beach.protocol.controller.BeachBrokerAddReq;

public class AddTwsBrokerGen {

	public static void main(String[] args) {
		BeachBrokerAddReq req = new BeachBrokerAddReq();
		TwsBrokerType twsType = new TwsBrokerType();
		twsType.setHost("localhost");
		twsType.setPort(7495);
		twsType.setIdentifier("DLK Paper");
		twsType.setConnectionId(4);
		req.setBroker(twsType);
		try {
			String ser = DJson.serializePretty(req);
			System.out.println(ser);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
