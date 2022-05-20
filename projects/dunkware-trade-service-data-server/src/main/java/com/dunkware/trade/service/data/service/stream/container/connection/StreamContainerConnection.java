package com.dunkware.trade.service.data.service.stream.container.connection;

import java.util.List;

import com.dunkware.trade.service.data.service.stream.container.StreamContainerConnector;
import com.dunkware.trade.service.data.service.stream.container.StreamContainerController;

public class StreamContainerConnection {

	public StreamContainerConnector connector; 
	
	private List<StreamConnectionHandler> handlers;
	
	public void start(StreamContainerConnector connector, StreamContainerController controller) throws Exception { 
		
	}
}
