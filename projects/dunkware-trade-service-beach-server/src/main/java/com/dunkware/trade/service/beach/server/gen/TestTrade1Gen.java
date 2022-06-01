package com.dunkware.trade.service.beach.server.gen;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.sdk.core.model.trade.TradeSide;
import com.dunkware.trade.sdk.core.model.trade.TradeType;
import com.dunkware.trade.sdk.lib.model.LimitAskEntryType;
import com.dunkware.trade.sdk.lib.model.MarketEntryType;
import com.dunkware.trade.sdk.lib.model.smart.SmartExitRuleType;
import com.dunkware.trade.sdk.lib.model.smart.SmartExitType;
import com.dunkware.trade.sdk.lib.model.smart.rules.TimerRuleType;
import com.dunkware.trade.service.beach.protocol.trade.pool.BeachPoolTradeReq;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerType;

public class TestTrade1Gen {
	
	public static void main(String[] args) {
		BeachPoolTradeReq req = new  BeachPoolTradeReq();
		req.setPool("Test Pool");
		TradeType tradeType = new TradeType();
		tradeType.setCapital(5000);
		MarketEntryType entryType = new MarketEntryType();
		LimitAskEntryType limitEntryType = new LimitAskEntryType();
		limitEntryType.setTimeout(40);
		tradeType.setEntry(limitEntryType);
		SmartExitType exitType = new SmartExitType();
		List<SmartExitRuleType> ruleTypes = new ArrayList<SmartExitRuleType>();
		TimerRuleType timer = new TimerRuleType();
		timer.setTimer(30);
		ruleTypes.add(timer);
		exitType.setRules(ruleTypes);
		tradeType.setExit(exitType);
		tradeType.setSide(TradeSide.LONG);
		
		TradeTickerSpec ticker = new TradeTickerSpec();
		ticker.setType(TradeTickerType.Equity);
		ticker.setSymbol("SPY");
		ticker.setName("SPY");
		tradeType.setTicker(ticker);
		req.setTrade(tradeType);
		
		try {
			String ser = DJson.serializePretty(req);
			System.out.println(ser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
