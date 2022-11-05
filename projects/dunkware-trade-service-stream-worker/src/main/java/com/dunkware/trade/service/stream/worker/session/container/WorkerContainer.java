package com.dunkware.trade.service.stream.worker.session.container;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.spring.channel.Channel;
import com.dunkware.trade.service.stream.container.worker.WorkerContainerInput;
import com.dunkware.xstream.net.core.container.Container;

public interface WorkerContainer {

	/**
	 * Starts the worker container with the JSON serializable input 
	 * @param input
	 * @throws WorkerContainerException
	 */
	void start(WorkerContainerInput input) throws WorkerContainerException;
	
	/**
	 * Returns the exector 
	 * @return
	 */
	DExecutor getExecutor();
	
	/**
	 * Returns the underlying data container 
	 * @return
	 */
	Container getCache();
	
	/**
	 * Returns the messaging channel to the ServerContainer
	 * Here default ChannelHandlers are added with annotation channel type WorkerContainer
	 * and handlers can be added dynamically. 
	 * @return
	 */
	Channel getChannel();
	
	/**
	 * Disposes the container and its resources 
	 */
	void dispose(); 
	

	
	
	
	
	

}
