package com.dunkware.spring.cluster;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("DunkNet")
public class DunkNetConfig {
	
	@Value("${dunknet.node.id}")		
	private String nodeId; 
	
	@Value("#{'${dunknet.node.profiles}'.split(',')}")
	private List<String>profiles;
	
	@Value("${dunknet.brokers}")
	private String serverBrokers;
	
	@Value("${dunknet.cluster.id}")
	private String clusterId;
	
	@Value("${dunknet.timeout:#{150}}")
	private int timeout; 
	
	@Value("${dunknet.handler.threads:#{4}}")
	private int handlerThreads;

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public List<String> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<String> profiles) {
		this.profiles = profiles;
	}

	public String getServerBrokers() {
		return serverBrokers;
	}

	public void setServerBrokers(String serverBrokers) {
		this.serverBrokers = serverBrokers;
	}

	public String getClusterId() {
		return clusterId;
	}

	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getHandlerThreads() {
		return handlerThreads;
	}

	public void setHandlerThreads(int handlerThreads) {
		this.handlerThreads = handlerThreads;
	} 
	
	
	
	
	
	
	

}
