package com.dunkware.trade.service.beach.model.gen;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.beach.model.trade.BeachExitTriggerModel;
import com.dunkware.trade.service.beach.model.trade.BeachTradeModel;

public class Test1TradeGen {
	
	public static void main(String[] args) {
		BeachTradeModel model = new BeachTradeModel();
		model.setSide("LONG");
		model.setEnableEntryCap(false);
		model.setEnableEntryOffset(false);
		model.setEnableStop(false);
		model.setCapital(5000);
		model.setSymbol("SPY");
		model.setEnableEntryTimeout(false);
		BeachExitTriggerModel timmer = new BeachExitTriggerModel();
		timmer.setType("Timer");
		timmer.setTimer(30);
		timmer.setTimerUnit("SECOND");
		model.getExits().add(timmer);
		try {
			System.out.println(DJson.serializePretty(model));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
