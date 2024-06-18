package com.dunkware.trade.broker.tws.tests;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.broker.tws.TwsBrokerType;

public class TwsBrokerBeanPrint {

	public static void main(String[] args) {
		TwsBrokerType bean = new TwsBrokerType();
		bean.setHost("localhost");
		bean.setIdentifier("TwsPaper");
		bean.setPort(7495);
		try {
			String ser = DJson.serializePretty(bean);
			System.out.println(ser);
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
}
