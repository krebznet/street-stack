package com.dunkware.trade.sdk.lib.runtime.signal;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.trade.sdk.core.runtime.system.SystemException;
import com.dunkware.trade.sdk.core.runtime.trade.Trade;
import com.dunkware.trade.sdk.core.runtime.trade.event.ETradeClosed;
import com.dunkware.trade.sdk.lib.model.signal.SignalTradeType;

public class SignalTrade {
	
	private SignalTradeType type; 
	private SignalSystem system; 
	
	private List<Trade> activeTrades = new ArrayList<Trade>();
	
	public void init(SignalTradeType type, SignalSystem system) throws SystemException { 
		this.type = type; 
		this.system = system; 
	}
	
	
	
	public void onSignal(Object spec) { 
		// count open trades in general if < max 
		// count open trades with this symbol if < max
		
	}
	
	
	private void openTrade(Object spec) { 
		// execute a trade and listen on it.
	}
	
	
	@ADEventMethod()
	public void tradeClosed(ETradeClosed closed) { 
		// decrement active trade count right? 
	}
	
	

}
