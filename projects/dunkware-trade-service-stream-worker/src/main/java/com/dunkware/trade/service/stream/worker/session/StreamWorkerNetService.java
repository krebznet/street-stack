package com.dunkware.trade.service.stream.worker.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.spring.cluster.DunkNetChannelServer;
import com.dunkware.spring.cluster.anot.ADunkNetChannel;
import com.dunkware.spring.cluster.anot.ADunkNetComponent;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartReq;

@ADunkNetComponent
public class StreamWorkerNetService  {
	
	@Autowired
	private ApplicationContext ac;
	
	
	@ADunkNetChannel(returnType = Object.class)
	public StreamWorkerNode workerChannel(StreamSessionWorkerStartReq req) throws Exception { 
		StreamWorkerNode node = new StreamWorkerNode();
		ac.getAutowireCapableBeanFactory().autowireBean(node);
		node.init(req);
		return node;
	}
	

	
	

}
