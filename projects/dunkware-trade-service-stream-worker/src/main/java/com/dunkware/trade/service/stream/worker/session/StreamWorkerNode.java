package com.dunkware.trade.service.stream.worker.session;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.DunkNetChannelListener;
import com.dunkware.spring.cluster.DunkNetComponent;
import com.dunkware.spring.cluster.anot.ADunkNetService;
import com.dunkware.trade.service.stream.json.worker.stream.StartSignalHandlerReq;
import com.dunkware.trade.service.stream.json.worker.stream.StartSignalHandlerResp;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartReq;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamInput;


public class StreamWorkerNode extends DunkNetComponent implements DunkNetChannelListener {

	private StreamSessionWorkerStartReq req; 
		
	private DunkNetChannel channel;
	
	private XStreamInput input = new XStreamInput();
	
	private StreamWorkerStatProvider statProvider; 
	
	@Autowired
	private ApplicationContext ac; 
	
	private XStream stream;
	
	public void init(DunkNetChannel channel, StreamSessionWorkerStartReq req) throws Exception { 
		this.channel = channel;
		channel.addListener(this);
		channel.addComponent(this);
		this.req = req; 
		
	}
	
	public XStream getXStream() { 
		return stream;
	}
	
	
	
	@ADunkNetService()
	public StartSignalHandlerResp startSignalHandler(StartSignalHandlerReq req) { 
		return null;
	}

	@Override
	public void channelOpen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void channelClose() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	// signal Query  
	
	// Entity Query 
	
	// Entity Scanner 
	
	// we can stream stats if we want 
	
	
}
