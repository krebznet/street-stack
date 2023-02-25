package com.dunkware.trade.service.beach.server.runtime.core.bot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.service.beach.protocol.spec.BeachBotState;
import com.dunkware.trade.service.beach.server.repository.BeachBotDO;
import com.dunkware.trade.service.beach.server.runtime.BeachAccount;
import com.dunkware.trade.service.beach.server.runtime.BeachBot;
import com.dunkware.trade.service.beach.server.runtime.BeachTrade;

import comm.dunkware.trade.service.beach.web.bot.WebBot;
import comm.dunkware.trade.service.beach.web.bot.WebBotPlay;

public class BeachBotImpl implements BeachBot {

	@Autowired
	private ApplicationContext ac;
	
	private BeachBotDO entity; 
	
	WebBot webBot; 
	
	private DEventNode eventNode;
	private BeachAccount account; 
	
	private BeachBotState state = BeachBotState.Stopped;
	
	
	private List<BeachBotPlay> plays = new ArrayList<BeachBotPlay>();
	private Semaphore playLock = new Semaphore(1);
	
	// make a wrapper; 
	private List<BeachTrade> activeTrades;
	
	public void init(BeachAccount account, BeachBotDO entity) { 
		this.account = account; 
		this.entity = entity;
	}
	
	@Override
	public void start() throws Exception {
		for (WebBotPlay webPlay : webBot.getPlays()) {
			BeachBotPlay tradePlay = new BeachBotPlay();
			ac.getAutowireCapableBeanFactory().autowireBean(tradePlay);
			try {
				tradePlay.init(webPlay, this);;
			} catch (Exception e) {
				throw e;
			}
			
		}
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
	
	public BeachTrade createTrade(BeachBotPlay play) throws Exception { 
		return null;
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
	
	

	
}
