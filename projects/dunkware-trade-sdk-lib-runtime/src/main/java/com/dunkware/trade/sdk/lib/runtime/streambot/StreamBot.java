package com.dunkware.trade.sdk.lib.runtime.streambot;

import com.dunkware.trade.sdk.core.model.system.SystemType;
import com.dunkware.trade.sdk.core.runtime.system.System;
import com.dunkware.trade.sdk.core.runtime.system.SystemException;
import com.dunkware.trade.sdk.core.runtime.system.anot.ASystem;
import com.dunkware.trade.sdk.core.runtime.trade.TradeSession;
import com.dunkware.trade.sdk.lib.model.streambot.model.StreamBotType;
import com.dunkware.trade.sdk.lib.model.streambot.web.WebStreamBot;


@ASystem(type = StreamBotType.class)
public class StreamBot implements System {

	private StreamBot type;

	// yup exactly you need some fuckin integration channel? 
	// create a scannerChannel(ScannerChannelRequest") -> to the StreamController
	// then it will do its magic 
	// inserts --> 
	private StreamBotType myType;
	@Override
	public void init(SystemType type, TradeSession context) throws SystemException {
		myType = (StreamBotType)type;
		
		
		
	}

	@Override
	public void stop(boolean liquidate) {
		// TODO Auto-generated method stub
		
	}
	
	

}
