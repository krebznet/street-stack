package com.dunkware.trade.engine.test.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.trade.broker.tws.TwsAccount;
import com.dunkware.trade.broker.tws.TwsBroker;
import com.dunkware.trade.engine.api.TradeEngine;
import com.dunkware.trade.engine.test.bot.model.TestBotStarter;
import com.dunkware.utils.core.concurrent.DunkExecutor;
import com.dunkware.utils.core.events.DunkEventNode;
import com.dunkware.utils.core.events.DunkEventTree;

public class TestBot {
	
	private static TestBot instance; 
	
	public static TestBot get() { 
		if(instance == null) { 
			instance = new TestBot();
		}
		return instance;
	}
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private TwsBroker broker;
	
	private TwsAccount account;
	
	private TradeEngine tradeEngine; 
	
	private DunkEventNode eventNode; 
	
	private TestBotStarter botStarter; 
	
	private DunkEventTree eventTree; 
	
	private DunkExecutor executor; 
	
	private TestBotStatus status = TestBotStatus.NullBot;
	
	private TestBot() { 
		eventTree = DunkEventTree.newInstance(new DunkExecutor(4));
		eventNode = eventTree.getRoot();
	}

	public void startBot(TestBotStarter starter) throws Exception { 
		eventTree =  DunkEventTree.newInstance(executor);
		eventNode = eventTree.getRoot();
	}

	public TestBotStatus getStatus() { 
		return status;
	}
	
	public DunkEventNode getEventNode() { 
		return eventNode; 
	}
	
	public TwsBroker getBroker() throws Exception { 
		return broker; 
	}
	
	public TradeEngine getTradeEngine() throws Exception  { 
		return tradeEngine;
	}
	
	
	

}
