package com.dunkware.trade.service.stream.server.session.capture;

import java.util.ArrayList;
import java.util.List;

import org.antlr.grammar.v3.ANTLRv3Parser.exceptionGroup_return;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.trade.service.stream.server.cluster.ClusterNode;
import com.dunkware.trade.service.stream.server.cluster.ClusterService;

public class SessionEventCaptureService {
	
	@Autowired
	private ClusterService clusterService; 
	
	private SessionEventCaptureConfig config;
	
	private List<SessionEventCapture> runningCaptures = new ArrayList<SessionEventCapture>();
	
	public SessionEventCapture startEventCapture(SessionEventCaptureConfig config) throws Exception {
		this.config = config; 
		ClusterNode[] nodes = clusterService.getGreeNodes("MongoEventCapture");
		if(nodes.length < config.getWorkers()) { 
			throw new Exception("Mongo Evenet Cpature Config Contains More Workers than available nodes");
		}
		
		return null;
	}

}
