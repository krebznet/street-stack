package com.dunkware.trade.broker.tws.connector;

public interface TwsConnectorService {
	
	public void initService(TwsConnector connector);
	
	public void startService(); 
	
	public void disposeService();

}
