package com.dunkware.trade.service.beach.model.system.signal;

import java.time.LocalTime;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.beach.model.system.common.TradeEntryOrderData;
import com.dunkware.trade.service.beach.model.system.common.TradeExitOrderData;
import com.dunkware.trade.service.beach.model.system.common.TradeExitTrigger;
import com.dunkware.trade.service.beach.model.system.common.TradeExitTriggerType;
import com.dunkware.trade.service.beach.model.system.common.TradeSide;
import com.dunkware.trade.service.beach.model.system.common.TradeType;

public class SignalSystemFactory {

	public static void main(String[] args) {
		SignalSystem system = new SignalSystem();
		system.setAccountId(3);
		system.setAllocatedCapital(3000.30);
		system.setEnableSchedule(true);
		system.getScheduleDays().add("Monday");
		system.getScheduleDays().add("Tuesday");
		system.setScheduleStartTime(LocalTime.of(9, 30,00));
		system.setScheduleStopTime(LocalTime.of(23, 3,32));
		
		SignalSystemTrade trade1 = new SignalSystemTrade();
		trade1.setTradeCapital(5000);
		trade1.setSide(TradeSide.LONG);
		trade1.setTradeCapitalLimit(15000.0);
		trade1.setEnableStopLoss(true);
		trade1.setStopLoss(3.0);
		trade1.getSignals().add(1);
		trade1.getSignals().add(3);
		trade1.setEnableActiveTradeLimit(false);
		trade1.setEnableActiveSymbolLimit(true);
		trade1.setActiveSymbolLimit(1);
		TradeEntryOrderData entryOrderData = new TradeEntryOrderData();
		entryOrderData.setType(TradeType.PEGGED);
		entryOrderData.setEnableEntryTimeout(true);
		entryOrderData.setEntryTimeout(49);
		entryOrderData.setPegOffsetEnabled(true);
		entryOrderData.setPegOffset(1.0);
		trade1.setEntryOrderData(entryOrderData);
		TradeExitOrderData exitOrderData = new TradeExitOrderData();
		exitOrderData.setType(TradeType.PEGGED);
		trade1.setExitOrderData(exitOrderData);
		
		TradeExitTrigger timerTrigger = new TradeExitTrigger();
		timerTrigger.setType(TradeExitTriggerType.Timer);
		timerTrigger.setTimer(49);
		trade1.getExitTriggers().add(timerTrigger);
		TradeExitTrigger trailingStopTrigger = new TradeExitTrigger();
		trailingStopTrigger.setType(TradeExitTriggerType.TrailingStop);
		trailingStopTrigger.setTrailingPercent(1.5);
		trade1.getExitTriggers().add(trailingStopTrigger);
		system.getTrades().add(trade1);
		
		try {
			System.out.println(DJson.serializePretty(system));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
}
