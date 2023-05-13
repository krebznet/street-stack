package com.dunkware.trade.service.beach.protocol.gen;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.beach.protocol.broker.AddBrokerReq;

public class AddLocalhostBroker {

	public static void main(String[] args) {
		AddBrokerReq add = new AddBrokerReq();
		add.setHost("localhost");
		add.setPort(7495);
		add.setClientId(4);
		add.setName("Tws Paper");
		try {
			System.out.println(DJson.serializePretty(add));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
