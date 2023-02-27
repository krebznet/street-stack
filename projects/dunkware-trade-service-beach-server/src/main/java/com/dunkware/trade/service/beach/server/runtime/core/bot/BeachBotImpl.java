package com.dunkware.trade.service.beach.server.runtime.core.bot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.sdk.core.model.trade.TradeType;
import com.dunkware.trade.sdk.core.runtime.trade.Trade;
import com.dunkware.trade.sdk.core.runtime.trade.TradeList;
import com.dunkware.trade.service.beach.protocol.spec.BeachBotState;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.repository.BeachBotDO;
import com.dunkware.trade.service.beach.server.repository.BeachEntryDO;
import com.dunkware.trade.service.beach.server.repository.BeachExitDO;
import com.dunkware.trade.service.beach.server.repository.BeachTradeDO;
import com.dunkware.trade.service.beach.server.runtime.BeachAccount;
import com.dunkware.trade.service.beach.server.runtime.BeachBot;
import com.dunkware.trade.service.beach.server.runtime.BeachTrade;
import com.dunkware.trade.service.beach.server.runtime.core.BeachEntryImpl;
import com.dunkware.trade.service.beach.server.runtime.core.BeachTradeImpl;
import com.dunkware.trade.tick.api.instrument.Instrument;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

import comm.dunkware.trade.service.beach.web.bot.WebBot;
import comm.dunkware.trade.service.beach.web.bot.WebBotPlay;

public class BeachBotImpl implements BeachBot {

	@Autowired
	private ApplicationContext ac;
	
	@Autowired
	private BeachRuntime beachRuntime; 
	
	private BeachBotDO entity; 
	
	WebBot webBot; 
	
	private DEventNode eventNode;
	private BeachAccount account; 
	
	private BeachBotState state = BeachBotState.Stopped;
	
	
	private List<BeachBotPlay> plays = new ArrayList<BeachBotPlay>();
	private Semaphore playLock = new Semaphore(1);
	
	private TradeList trades = new TradeList();
	
	public void init(BeachAccount account, BeachBotDO entity) throws Exception { 
		this.account = account; 
		this.entity = entity;
		eventNode = account.getEventNode().createChild("/bots/" + entity.getName());
		try {
			webBot = DJson.getObjectMapper().readValue(entity.getModel(), WebBot.class);
		} catch (Exception e) {
			throw new Exception("Bot deserizlie exception " + e.toString());
		}
	}
	
	@Override
	public void start() throws Exception {
		Thread starter = new Thread() { 
			public void run() { 
				for (WebBotPlay webPlay : webBot.getPlays()) {
					BeachBotPlay tradePlay = new BeachBotPlay();
					ac.getAutowireCapableBeanFactory().autowireBean(tradePlay);
					try {
						tradePlay.init(webPlay, BeachBotImpl.this);;
					} catch (Exception e) {
						state = BeachBotState.Exception;
					}
					
				}		
			}
		}; 
		starter.run();
	}


	@Override
	public void stop() throws Exception {
		if(state != state.Running) { 
			throw new Exception("Beach Bot is not running, cannot stop it");
		}
		
	}

	@Override
	public BeachBotDO getEntity() {
		return entity;
	}
	
	@Override
	public BeachBotState getState() {
		return state;
	}

	@Override
	public DEventNode getEventNode() {
		return eventNode;
	}

	@Override
	public BeachAccount getAccount() {
		return account;
	}

	@Override
	public TradeList getTrades() {
		return trades;
	}

	@Override
	public Trade createPlayTrade(BeachBotPlay play, TradeType type) throws Exception {
		BeachTradeImpl trade = new BeachTradeImpl();
		ac.getAutowireCapableBeanFactory().autowireBean(trade);
		trade.create(this, play.getModel().getName(), type);
		trades.insert(trade);
		return trade;
	}

	@Override
	public void execute(Runnable runnable) {
		 beachRuntime.getExecutor().execute(runnable);
		
	}

	@Override
	public Instrument getInstrument(TradeTickerSpec tickerSpec) throws Exception {
		return account.getBroker().acquireInstrument(tickerSpec);
	}
	
	

	

	
	
	
	
	

	
}
