package com.dunkware.trade.service.stream.server.controller.session.container.connection;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.net.proto.netstream.GNetServerMessage;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainer;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerConnector;
import com.dunkware.trade.service.stream.server.controller.session.container.connection.extensions.EntitySearchExtension;

public class SessionContainerConnection {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public SessionContainerConnector connector; 
	
	private List<SessionContainerExtension> extensions = new ArrayList<SessionContainerExtension>();
	
	private SessionContainer container;
	
	private MessageRouter messageRouter;
	
	public void start(SessionContainerConnector connector, SessionContainer controller) throws Exception { 
		this.connector = connector; 
		this.container = controller;
		EntitySearchExtension entSearchHandler = new EntitySearchExtension();
		entSearchHandler.create(controller, this);
		this.extensions.add(entSearchHandler);
		messageRouter = new MessageRouter();
		messageRouter.start();
	}
	
	public void sendMessage(GNetServerMessage message) {
		connector.sendMessage(message);
	}
	
	public SessionContainer getController() { 
		return container;
	}
	
	private class MessageRouter extends Thread { 
		public void run() { 
			while(!interrupted()) { 
				try {
					GNetClientMessage message = connector.getMessageQueue().take();
					for (SessionContainerExtension extension : extensions) {
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
