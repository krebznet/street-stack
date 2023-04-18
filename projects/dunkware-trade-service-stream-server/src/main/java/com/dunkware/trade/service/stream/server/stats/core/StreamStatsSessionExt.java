package com.dunkware.trade.service.stream.server.stats.core;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.trade.service.stream.server.controller.session.StreamSession;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionNode;
import com.dunkware.trade.service.stream.server.controller.session.anot.AStreamSessionExt;
import com.dunkware.xstream.core.stats.StreamStatsExtType;


@AStreamSessionExt()
public class StreamStatsSessionExt implements StreamSessionExtension {

	private StreamSession session;
	
	@Autowired
	private Cluster cluster; 
	
	@Override
	public void sessionStarting(StreamSession session) {
		this.session = session; 
	}

	@Override
	public void nodeStarting(StreamSessionNode node) {
		StreamStatsExtType ext = new StreamStatsExtType();
		ext.setSessionId(session.getSessionId());
		ext.setStreamIdent(session.getStream().getName()); 
		ext.setPostURL(cluster.httpURL("/stats/payload/session"));
		ext.setPostId(node.getNodeId());
		ext.setBaseURL(cluster.getConfig().getServerHttp());
		node.getStreamBundle().getExtensions().add(ext);
	}

	@Override
	public void nodeStarted(StreamSessionNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionStarted(StreamSession session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionStopping(StreamSession session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionStopped(StreamSession session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nodeStartException(StreamSessionNode node, String exception) {
		// TODO Auto-generated method stub
		
	}
	
	

}
