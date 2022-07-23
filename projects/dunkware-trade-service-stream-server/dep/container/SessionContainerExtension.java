package com.dunkware.trade.service.stream.server.controller.session.container.connection;

import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainer;

public interface SessionContainerExtension {

	public void create(SessionContainer controller, SessionContainerConnection connection); 
	
	public void dispose();
	
	public void onMessage(GNetClientMessage message);
}
