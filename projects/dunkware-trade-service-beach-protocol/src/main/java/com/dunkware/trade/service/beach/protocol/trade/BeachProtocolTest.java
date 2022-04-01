package com.dunkware.trade.service.beach.protocol.trade;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.sdk.core.model.order.OrderAction;
import com.dunkware.trade.sdk.core.model.order.OrderKind;
import com.dunkware.trade.sdk.core.model.order.OrderType;
import com.dunkware.trade.sdk.core.model.trade.TradeSide;
import com.dunkware.trade.sdk.core.model.trade.TradeType;
import com.dunkware.trade.sdk.lib.model.MarketEntryType;
import com.dunkware.trade.sdk.lib.model.smart.SmartExitType;
import com.dunkware.trade.sdk.lib.model.smart.rules.GainLossRuleType;
import com.dunkware.trade.sdk.lib.model.smart.rules.TimerRuleType;
import com.dunkware.trade.sdk.lib.model.smart.rules.TrailingStopRuleType;
import com.dunkware.trade.service.beach.protocol.trade.pool.BeachPoolTradeReq;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerType;

public class BeachProtocolTest {
	
	public static void main(String[] args) {
		BeachPoolTradeReq req = new BeachPoolTradeReq();
		TradeType tradeType = new TradeType();
		tradeType.setSide(TradeSide.LONG);
		tradeType.setCapital(10000.0);
		TradeTickerSpec ticker = new TradeTickerSpec();
		ticker.setType(TradeTickerType.Equity);
		ticker.setSymbol("GME");
		tradeType.setTicker(ticker);
		req.setTrade(tradeType);
		req.setPool("Test");
		
		// Market Entry 
		MarketEntryType entry = new MarketEntryType();
		tradeType.setEntry(entry);
		
		// Smart Exit 
		SmartExitType exit = new SmartExitType();
		tradeType.setExit(exit);
		
		TrailingStopRuleType trailing = new TrailingStopRuleType();
		trailing.setTrail(1.0);
		trailing.setStop(3.0);
		exit.getRules().add(trailing);
		try {
			System.out.println(DJson.serializePretty(req));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public static void mainGainLoss(String[] args) {
		BeachPoolTradeReq req = new BeachPoolTradeReq();
		TradeType tradeType = new TradeType();
		tradeType.setSide(TradeSide.LONG);
		tradeType.setCapital(10000.0);
		TradeTickerSpec ticker = new TradeTickerSpec();
		ticker.setType(TradeTickerType.Equity);
		ticker.setSymbol("GME");
		tradeType.setTicker(ticker);
		req.setTrade(tradeType);
		req.setPool("Test");
		
		// Market Entry 
		MarketEntryType entry = new MarketEntryType();
		tradeType.setEntry(entry);
		
		// Smart Exit 
		SmartExitType exit = new SmartExitType();
		tradeType.setExit(exit);
		
		// UPL -3.0P
		GainLossRuleType loss = new GainLossRuleType();
		loss.setTrigger(-1.0);
		exit.getRules().add(loss);
		
		// UPL -1.5P
		GainLossRuleType gain = new GainLossRuleType();
		gain.setTrigger(1.5);
		exit.getRules().add(gain);
		
		try {
			System.out.println(DJson.serializePretty(req));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public static void mainTimer(String[] args) {
		BeachPoolTradeReq req = new BeachPoolTradeReq();
		TradeType tradeType = new TradeType();
		tradeType.setSide(TradeSide.LONG);
		tradeType.setCapital(10000.0);
		TradeTickerSpec ticker = new TradeTickerSpec();
		ticker.setType(TradeTickerType.Equity);
		ticker.setSymbol("GME");
		tradeType.setTicker(ticker);
		req.setTrade(tradeType);
		req.setPool("Test");
		
		// Market Entry 
		MarketEntryType entry = new MarketEntryType();
		tradeType.setEntry(entry);
		
		// Smart Exit 
		SmartExitType exit = new SmartExitType();
		tradeType.setExit(exit);
		
		// Timer Rule 
		TimerRuleType timer = new TimerRuleType();
		timer.setTimer(20);
		exit.getRules().add(timer);
		
		
		try {
			System.out.println(DJson.serializePretty(req));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public static void main3(String[] args) {
		BeachPoolTradeReq req = new BeachPoolTradeReq();
		MarketEntryType entry = new MarketEntryType();
		TradeType tradeType = new TradeType();
		tradeType.setEntry(entry);
		SmartExitType exit = new SmartExitType();
		tradeType.setSide(TradeSide.LONG);
		//exit.getRules().add(trailing);
		GainLossRuleType loss = new GainLossRuleType();
		loss.setTrigger(-1.0);
		exit.getRules().add(loss);
		GainLossRuleType gain = new GainLossRuleType();
		gain.setTrigger(1.0);
		exit.getRules().add(gain);
		tradeType.setExit(exit);
		tradeType.setCapital(10000.0);
		TradeTickerSpec ticker = new TradeTickerSpec();
		ticker.setType(TradeTickerType.Equity);
		ticker.setSymbol("GME");
		tradeType.setTicker(ticker);
		req.setTrade(tradeType);
		req.setPool("Test");
		try {
			System.out.println(DJson.serializePretty(req));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	public static void main2(String[] args) {
		OrderType type = new OrderType();
		type.setAction(OrderAction.BUY);
		type.setKind(OrderKind.MKT);
		type.setSize(50);
		
		TradeTickerSpec ticker = new TradeTickerSpec();
		ticker.setType(TradeTickerType.Equity);
		ticker.setSymbol("AAPL");
		type.setTicker(ticker);
		BeachOrderSubmitReq req = new BeachOrderSubmitReq();
		req.setBroker("TwsPaper");
		req.setAccount("DU222846");
		req.setOrder(type);
		try {
			System.out.println(DJson.serializePretty(req));			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
