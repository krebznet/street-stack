package com.dunkware.net.cluster.json.node;

public class ClusterNodeServiceDescriptor {
	
	private String className; 
	private ClusterNodeServiceType type;
	private String endpoint;
	
	public ClusterNodeServiceType getType() {
		return type;
	}
	public void setType(ClusterNodeServiceType type) {
		this.type = type;
	}
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	} 
	
	

	
}
