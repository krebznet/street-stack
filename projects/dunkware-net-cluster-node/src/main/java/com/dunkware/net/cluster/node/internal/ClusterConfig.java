package com.dunkware.net.cluster.node.internal;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dunkware.net.cluster.json.node.ClusterNodeType;

@Service
public class ClusterConfig {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${net.cluster.node.id}")		
	private String nodeId; 
	
	@Value("${net.cluster.node.type}")
	private String nodeType; 
	
	@Value("${net.cluster.server.brokers}")
	private String serverBrokers;
	
	@Value("${net.cluster.server.grpc}")
	private String serverGrpc; 
	
	@Value("${net.cluster.server.http}")
	private String serverHttp;

	@Value("${net.cluster.cluster.grpc}")
	private String clusterGrpc; 
	
	public String getNodeId() {
		return nodeId;
	}
	
	@PostConstruct
	public void load() { 
		logger.debug("My Grpc Server " + serverGrpc);
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

	public String getClusterGrpc() {
		return clusterGrpc;
	}
	
	
	
	
	
	

}
