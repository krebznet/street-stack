package com.dunkware.trade.service.beach.protocol.trade.transport;

import com.dunkware.common.util.data.NetBean;
import com.dunkware.common.util.data.NetList;
import com.dunkware.common.util.json.DJson;

public class Test {
	
	public static void main(String[] args) {
		NetBean bean = new NetBean();
		bean.setValue("account", "Schwab Simulated");
		bean.setValue("name", "Breakout Bot");
		bean.setValue("allocatedCapital", 40000.0);
		bean.setValue("activeCapital", 2332.23);
		bean.setValue("activeTrades", 4);
		bean.setValue("totalTrades", 15);
		bean.setValue("realizedPLP", 2.3);
		bean.setValue("realizedPL", 1324.32);
		bean.setValue("unrealizedPL", -240);
		bean.setValue("unrealizedPLP", 0.4);
		
		NetList list = new NetList();
		list.insert(bean);
		list.insert(bean);
		try {
			System.out.println(DJson.serializePretty(list));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
