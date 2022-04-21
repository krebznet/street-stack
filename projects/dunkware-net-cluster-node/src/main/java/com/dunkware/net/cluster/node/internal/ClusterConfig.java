package com.dunkware.net.cluster.node.internal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dunkware.net.cluster.json.node.ClusterNodeType;

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

	public ClusterNodeType getNodeType() {
		if(nodeType.equalsIgnoreCase(ClusterNodeType.Worker.name())) {
			return ClusterNodeType.Worker;
		}
		if(nodeType.equalsIgnoreCase(ClusterNodeType.Service.name())) {
			return ClusterNodeType.Service;
		}
		return ClusterNodeType.Unknown;
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
