package com.dunkware.trade.service.beach.protocol.trade;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.sdk.core.model.trade.TradeSide;
import com.dunkware.trade.sdk.lib.model.bot.TradeBotPlayType;
import com.dunkware.trade.sdk.lib.model.bot.TradeBotType;
import com.dunkware.trade.sdk.lib.model.entry.MarketEntryType;
import com.dunkware.trade.sdk.lib.model.exit.SmartExitRuleType;
import com.dunkware.trade.sdk.lib.model.exit.SmartExitType;
import com.dunkware.trade.sdk.lib.model.exit.rules.SmartExitTimer;
import com.dunkware.trade.service.beach.protocol.trade.service.BeachSystemAddReq;

public class BeachProtocolTest {
	/*
	 * public static void main(String[] args) {
	 * 
	 * TradeType tradeType = new TradeType(); tradeType.setSide(TradeSide.LONG);
	 * tradeType.setCapital(10000.0); TradeTickerSpec ticker = new
	 * TradeTickerSpec(); ticker.setType(TradeTickerType.Equity);
	 * ticker.setSymbol("GME"); tradeType.setTicker(ticker);
	 * req.setTrade(tradeType); req.setPool("Test");
	 * 
	 * // Market Entry MarketEntryType entry = new MarketEntryType();
	 * tradeType.setEntry(entry);
	 * 
	 * // Smart Exit SmartExitType exit = new SmartExitType();
	 * tradeType.setExit(exit);
	 * 
	 * TrailingStopRuleType trailing = new TrailingStopRuleType();
	 * trailing.setTrail(1.0); trailing.setStop(3.0); exit.getRules().add(trailing);
	 * try { System.out.println(DJson.serializePretty(req)); } catch (Exception e) {
	 * e.printStackTrace(); // TODO: handle exception }
	 */
//	}
	
	public static void main(String[] args) {
		TradeBotType bot = new TradeBotType();
		bot.setSymbolActiveLimit(1);
		bot.setSymbolThrottle(30);
		bot.setTradeActiveLimit(5);
		bot.setName("Acid Test 1");
		bot.setAllocatedCapital(100000);
		TradeBotPlayType play = new TradeBotPlayType();
		play.setStream("us_equity");
		play.setSignal("Event1");
		play.setCapital(2500);
		play.setName("Event 1");
		play.setTradeSide(TradeSide.LONG);
		MarketEntryType entry = new MarketEntryType();
		play.setEntyType(entry);
		SmartExitType exitType = new SmartExitType();
		List<SmartExitRuleType> exitRules = new ArrayList<SmartExitRuleType>();
		SmartExitTimer timer = new SmartExitTimer();
		timer.setTimer(15);
		exitRules.add(timer);
		exitType.setRules(exitRules);
		play.setExitType(exitType);
		bot.getPlays().add(play);
		BeachSystemAddReq req = new BeachSystemAddReq();
		req.setAccountId(1);
		req.setName("Acid Test 1");
		req.setSystemType(bot);
		
		
		try {
			System.out.println(DJson.serializePretty(req));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	
		
		
		
	}
}
