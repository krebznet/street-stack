package com.dunkware.trade.service.beach.server.session;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.trade.tick.api.instrument.Instrument;

public interface BeachSessionTrade {
	
	public BeachSessionTradeBean getBean();
	
	public Instrument getInstrument();
	
	public BeachSessionTradeStatus getStatus();
	
	public DEvent getEventNode(); 
	
	public void closeTrade(BeachSessionTradeExit exit);
	
	

}
