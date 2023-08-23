package com.dunkware.trade.service.stream.worker.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.DunkNetComponent;
import com.dunkware.spring.cluster.anot.ADunkNetChannel;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartReq;

@Service
public class StreamWorkerNetService extends DunkNetComponent {
	
	@Autowired
	private ApplicationContext ac;
	
	@ADunkNetChannel(returnType = Object.class)
	public void workerChannel(DunkNetChannel channel,  StreamSessionWorkerStartReq req) throws Exception { 
		StreamWorkerNode node = new StreamWorkerNode();
		ac.getAutowireCapableBeanFactory().autowireBean(node);
		node.init(channel, req);
	}
	
	// sub channel --> 
	
	

}
