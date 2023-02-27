
package com.dunkware.trade.service.beach.server.gen;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.broker.tws.TwsBrokerType;
import com.dunkware.trade.sdk.lib.model.paper.PaperBrokerType;
import com.dunkware.trade.service.beach.protocol.controller.BeachBrokerAddReq;

public class AddPaperBrokerGen {

	public static void main(String[] args) {
		BeachBrokerAddReq req = new BeachBrokerAddReq();
		PaperBrokerType type = new PaperBrokerType();
		type.setIdentifier("Paper"); 
		type.setAccounts(5);
		req.setBroker(type);
		try {
			String ser = DJson.serializePretty(req);
			System.out.println(ser);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
