package com.dunkware.net.cluster.node.internal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ClusterConfig {
	
	@Value("${net.cluster.node.id}")		
	private String nodeId; 
	
	@Value("${net.cluster.node.type}")
	private String nodeType; 
	
	@Value("${net.cluster.server.brokers}")
	private String serverBrokers;
	
	@Value("${net.cluster.server.http}")
	private String serverGrpc; 
	
	@Value("${net.cluster.server.http}")
	private String serverHttp;

	public String getNodeId() {
		return nodeId;
	}

	public String getNodeType() {
		return nodeType;
	}

	public String getServerBrokers() {
		return serverBrokers;
	}

	public String getServerGrpc() {
		return serverGrpc;
	}

	public String getServerHttp() {
		return serverHttp;
	}
	
	
	
	

}
