package com.dunkware.trade.service.data.service.stream.container.connection;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.net.proto.netstream.GNetServerMessage;
import com.dunkware.trade.service.data.service.stream.container.StreamContainerConnector;
import com.dunkware.trade.service.data.service.stream.container.StreamContainerController;
import com.dunkware.trade.service.data.service.stream.container.connection.extensions.EntitySearchExtension;

public class StreamContainerConnection {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public StreamContainerConnector connector; 
	
	private List<StreamConnectionExtension> extensions = new ArrayList<StreamConnectionExtension>();
	
	private StreamContainerController controller;
	
	private MessageRouter messageRouter;
	
	public void start(StreamContainerConnector connector, StreamContainerController controller) throws Exception { 
		this.connector = connector; 
		this.controller = controller;
		EntitySearchExtension entSearchHandler = new EntitySearchExtension();
		entSearchHandler.create(controller, this);
		this.extensions.add(entSearchHandler);
		messageRouter = new MessageRouter();
		messageRouter.start();
	}
	
	public void sendMessage(GNetServerMessage message) {
		connector.sendMessage(message);
	}
	
	public StreamContainerController getController() { 
		return controller;
	}
	
	private class MessageRouter extends Thread { 
		public void run() { 
			while(!interrupted()) { 
				try {
					GNetClientMessage message = connector.getMessageQueue().take();
					for (StreamConnectionExtension extension : extensions) {
						extension.onMessage(message);
					}
				} catch (Exception e) {
					if (e instanceof InterruptedException) { 
						return;
					}
					logger.error("Error in connection message router " + e.toString());
				}
			}
		}
	}
}
