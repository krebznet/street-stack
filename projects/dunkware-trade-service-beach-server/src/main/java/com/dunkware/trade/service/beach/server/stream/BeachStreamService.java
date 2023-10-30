package com.dunkware.trade.service.beach.server.stream;

public interface BeachStreamService {
	
	public void addSignalListener(BeachSignalListener listener, int...signalIds);
	
	public void removeSignalListener(BeachSignalListener listener); 
	

}
