package com.dunkware.trade.engine.test.bot.event;

import com.dunkware.trade.engine.test.bot.TestBot;
import com.dunkware.utils.core.events.DunkEvent;

public class ETestBotEvent extends DunkEvent {

	protected TestBot bot; 
	
	public ETestBotEvent(TestBot bot) { 
		this.bot = bot; 
	}
}
