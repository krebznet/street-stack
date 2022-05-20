package com.dunkware.trade.service.data.service.stream.container.connection;

import com.dunkware.trade.service.data.service.stream.container.StreamContainerController;

public interface StreamConnectionHandler {

	public void init(StreamContainerController controller, StreamContainerConnection connection); 
	
	public void dispose();
}
