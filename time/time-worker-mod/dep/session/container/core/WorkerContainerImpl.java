package com.dunkware.trade.service.stream.worker.session.container.core;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.spring.messaging.channel.Channel;
import com.dunkware.spring.messaging.channel.ChannelService;
import com.dunkware.trade.service.stream.container.worker.WorkerContainerInput;
import com.dunkware.trade.service.stream.worker.session.container.WorkerContainer;
import com.dunkware.trade.service.stream.worker.session.container.WorkerContainerException;
import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerInput;
import com.dunkware.xstream.net.core.container.core.ContainerImpl;

import net.minidev.json.parser.ContainerFactory;

public class WorkerContainerImpl implements WorkerContainer {

	private WorkerContainerInput input; 
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	
	@Autowired
	private Cluster cluster; 
	
	/**
	 * The container we are going to manage. 
	 */
	private Container container; 
	
	@Override
	public void start(WorkerContainerInput input) throws WorkerContainerException {
		this.input = input;
		
		Map<String,Object> injectables = new HashMap<String,Object>();
		injectables.put("Container", this);
		try {
			workerChannel = channelService.createChannel("WorkerContainer", input.getKafkaBroker(), input.getWorkerTopic(), input.getServerTopic(), injectables);	
		} catch (Exception e) {
			throw new WorkerContainerException("Exception creating worker container " + e.toString());
		}
		
		ContainerInput containerInput = new ContainerInput(input.getContainerExtensions(), cluster.getExecutor(), input.getTimeZone(), input.getStreamIdentifier());
		container = new ContainerImpl();
		try {
			container.start(containerInput);	
		} catch (Exception e) {
			throw new WorkerContainerException("Exception starting data container " + e.toString());
		}
		
		
	}
	
	
	@Override
	public DExecutor getExecutor() {
		return cluster.getExecutor();
	}


	@Override
	public Channel getChannel() {
		return workerChannel; 
	}

	@Override
	public void dispose() {
		//// dispose this worker container 
		
	}

	@Override
	public Container getCache() {
		return container; 
	}

	

	
}
