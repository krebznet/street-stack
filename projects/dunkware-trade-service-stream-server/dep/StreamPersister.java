package com.dunkware.stream.controller.ssession.extensions;

import org.springframework.beans.factory.annotation.Value;

import com.dunkware.stream.controller.ssession.StreamSession;
import com.dunkware.stream.controller.ssession.StreamSessionExtension;
import com.dunkware.stream.controller.ssession.StreamSessionNode;
import com.dunkware.stream.controller.ssession.anot.AStreamSessionExt;

@AStreamSessionExt()
public class StreamPersister implements StreamSessionExtension {

	
	
	@Value("${redis.host}")
	private String host; 
	
	@Value("${redis.port}")
	private int port; 
	
	private StreamSession session; 
	
	@Override
	public void sessionStarting(StreamSession session) {
		this.session = session;
		session.getInput().getProperties().put("redist.host", host);
		
	}

	@Override
	public void nodeStarting(StreamSessionNode node) {
		node.getStartReq().getStreamProperties().put("redis.host",host);
		System.out.println("break here");
		String fuck = String.valueOf(port);
		node.getStartReq().getStreamProperties().put("redis.port",fuck);
		node.getStartReq().getStreamProperties().put("redis.fuck", "dfdfd");
		System.out.println("break fuck");
		
	}
	
	

}
