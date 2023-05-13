package com.dunkware.trade.service.beach.server.session;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.runtime.executor.OrderExecutor;
import com.dunkware.trade.service.beach.protocol.play.Play;
import com.dunkware.trade.service.beach.server.runtime.BeachPlay;
import com.dunkware.xstream.model.signal.StreamSignal;
import com.dunkware.xstream.model.signal.StreamSignalListener;

public class BeachSession implements StreamSignalListener {

	private BeachPlay play; 

	private DEventNode eventNode; 
	
	public void start(Play play, BeachPlay beachPlay) throws Exception { 
		// SessionWrapper
			// ActiveCapital
			// RealizedPL. 
			// UnrealizedPL 
			// Open Orders 
			// 
	}

	public void stop() { 
		// stops the game. 
		
	}

	@Override
	public void onSignal(StreamSignal signal) {
		// TODO Auto-generated method stub
		// 
		String symbol = (String)signal.getVars().get("TickSymbol");
		// TradeInstrument - needed. 
		// we need to say "can we trade" 
		// if(play.canTrade() { 
		
	}
	
	private void openTrade() { 
		// take the spec. 
		// create a OrderExecutor -> 
			//  set side BUY 
			// set kind MARKET
			// set size 
			// set timeout. 
		// going to be Order
		OrderExecutor executor = new OrderExecutor();
		// wrap an order provider 
		// executorCompleted()
			// tradeOpened()
		
			// startExit() 
				// trigger framework
				// get a stop order submitted. 
			// on exit TradeClosing 
			// tradeClosed. 
		
	}
	
	
	
	
	public DEventNode getEventNode() { 
		return eventNode; 
	}
}
