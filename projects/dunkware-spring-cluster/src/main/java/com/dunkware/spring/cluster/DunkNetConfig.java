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
	
	
	
	
	

}
