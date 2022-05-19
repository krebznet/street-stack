package com.dunkware.xstream.net.server;

import java.util.Map;

public interface StreamServer {
	
	public Map<String,StreamConnection> getClients();

	public void start(StreamServerInput input) throws Exception;
	
	// does it wrap one or many containers 
	
}
