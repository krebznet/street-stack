package com.dunkware.trade.service.stream.worker.config.session;

import org.springframework.stereotype.Service;

import com.dunkware.spring.cluster.DunkNetComponent;
import com.dunkware.spring.cluster.anot.ADunkNetChannel;
import com.dunkware.spring.cluster.core.response.DunkNetChannelResponse;

@Service
public class StreamWorkerService extends DunkNetComponent {
	
	
	
	@ADunkNetChannel(returnType = Object.class)
	public DunkNetChannelResponse sessionNode(Object payload) { 
		return null;
	}
	
	

}
