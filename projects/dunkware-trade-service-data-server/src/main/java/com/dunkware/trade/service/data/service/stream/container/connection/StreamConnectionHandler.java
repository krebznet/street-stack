package com.dunkware.trade.service.data.service.stream.container.connection;

import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.trade.service.data.service.stream.container.StreamContainerController;

public interface StreamConnectionHandler {

	public void create(StreamContainerController controller, StreamContainerConnection connection); 
	
	public void dispose();
	
	public void handleMessage(GNetClientMessage message);
}
