package com.dunkware.trade.broker.tws.tests;

import com.dunkware.trade.broker.tws.TwsBrokerType;
import com.dunkware.utils.core.json.DunkJson;

public class TwsBrokerBeanPrint {

	public static void main(String[] args) {
		TwsBrokerType bean = new TwsBrokerType();
		bean.setHost("localhost");
		bean.setIdentifier("TwsPaper");
		bean.setPort(7495);
		try {
			String ser = DunkJson.serializePretty(bean);
			System.out.println(ser);
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
}
