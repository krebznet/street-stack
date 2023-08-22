package com.dunkware.trade.service.stream.worker.config.session;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.DunkNetComponent;
import com.dunkware.spring.cluster.anot.ADunkNetService;
import com.dunkware.trade.service.stream.json.worker.stream.StartSignalHandlerReq;
import com.dunkware.trade.service.stream.json.worker.stream.StartSignalHandlerResp;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartReq;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamInput;


public class StreamWorkerNode extends DunkNetComponent {

	private StreamSessionWorkerStartReq req; 
		
	private DunkNetChannel channel;
	
	private XStreamInput input = new XStreamInput();
	
	private StreamWorkerStatProvider statProvider; 
	
	@Autowired
	private ApplicationContext ac; 
	
	private XStream stream;
	
	public void init(DunkNetChannel channel, StreamSessionWorkerStartReq req) { 
		this.channel = channel;
		this.req = req; 
	}
	
	@ADunkNetService()
	public StartSignalHandlerResp startSignalHandler(StartSignalHandlerReq req) { 
		return null;
	}
	
	// signal Query  
	
	// Entity Query 
	
	// Entity Scanner 
	
	// we can stream stats if we want 
	
	
}
