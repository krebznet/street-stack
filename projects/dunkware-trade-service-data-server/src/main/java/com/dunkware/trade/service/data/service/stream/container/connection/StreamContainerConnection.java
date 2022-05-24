package com.dunkware.trade.service.data.service.stream.container.connection;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.net.proto.netstream.GNetServerMessage;
import com.dunkware.trade.service.data.service.stream.container.StreamContainerConnector;
import com.dunkware.trade.service.data.service.stream.container.StreamContainerController;

public class StreamContainerConnection {

	public StreamContainerConnector connector; 
	
	private List<StreamConnectionHandler> handlers = new ArrayList<StreamConnectionHandler>();
	
	private StreamContainerController controller;
	
	public void start(StreamContainerConnector connector, StreamContainerController controller) throws Exception { 
		this.connector = connector; 
		this.controller = controller;
	}
	
	public void sendMessage(GNetServerMessage message) {
		connector.sendMessage(message);
	}
	
	public StreamContainerController getController() { 
		return controller;
	}
}
