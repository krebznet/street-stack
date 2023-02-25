package com.dunkware.trade.service.beach.server.runtime.core.bot;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.trade.sdk.core.model.trade.EntryType;
import com.dunkware.trade.sdk.core.model.trade.ExitType;
import com.dunkware.trade.service.beach.server.runtime.BeachBot;
import com.dunkware.trade.service.beach.server.runtime.BeachTrade;

import comm.dunkware.trade.service.beach.web.bot.WebBotPlay;

public class BeachBotPlay {
	
	private WebBotPlay model;
	private BeachBot bot;
	
	private EntryType entryType; 
	private ExitType exitType; 
	
	// list of trades. right? 
	// 
	
	private List<BeachTrade> activeTrades = new ArrayList<BeachTrade>();
	private List<BeachTrade> completedTrades = new ArrayList<BeachTrade>();
	
	public void init(WebBotPlay play, BeachBot bot) throws Exception { 
		this.model = play; 
		this.bot = bot; 
		
		entryType = BeachBotHelper.toEntryType(play.getEntryStrategy());
		exitType = BeachBotHelper.toExitType(play.getExitStrategy());
		
		// todo signal listener. 
		play.getSignal();

		
	}
	
	
	public void onSignal(String signal) { 
		// get the ticker
		// logic how many open trades are there for me
		// the signal listener part 
		// the instrument part. 
		//bot.get
	}
	
	public int activeSymbolTradeCount(String symbol) { 
		return 0; 
	}
	
	public int sinceLastSymbolTrade(String symbol) { 
		return 0; 
	}

}
