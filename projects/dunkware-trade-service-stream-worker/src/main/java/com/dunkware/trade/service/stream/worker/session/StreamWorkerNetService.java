package com.dunkware.trade.service.stream.worker.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.spring.cluster.anot.ADunkNetChannel;
import com.dunkware.spring.cluster.anot.ADunkNetComponent;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerCreateReq;

@ADunkNetComponent
public class StreamWorkerNetService  {
	
	@Autowired
	private ApplicationContext ac;
	
	
	@ADunkNetChannel(label = "Create Stream Session Worker Channel")
	public StreamSessionWorkerChannel workerChannel(StreamSessionWorkerCreateReq req) throws Exception { 
		StreamSessionWorkerChannel node = new StreamSessionWorkerChannel();
		ac.getAutowireCapableBeanFactory().autowireBean(node);
		node.init();
		return node;
	}
	

	
	

}
