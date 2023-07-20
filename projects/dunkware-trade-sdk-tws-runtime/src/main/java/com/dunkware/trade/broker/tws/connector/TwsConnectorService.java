package com.dunkware.trade.broker.tws.connector;

import com.dunkware.trade.broker.tws.TwsBroker;

public interface TwsConnectorService {
	
	public void initService(TwsBroker broker);
	
	public void startService(); 
	
	public void disposeService();

}
