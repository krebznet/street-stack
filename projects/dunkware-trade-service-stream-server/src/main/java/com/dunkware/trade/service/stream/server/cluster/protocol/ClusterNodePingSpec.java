package com.dunkware.trade.service.stream.server.cluster.protocol;

import com.dunkware.common.util.dtime.DTime;

public class ClusterNodePingSpec {
	
	private String id; 
	private String[] profiles; 
	private String endpoint; 
	private double usedMem; 
	private double totalMem; 
	private double freeMem; 
	private DTime pingTime;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String[] getProfiles() {
		return profiles;
	}
	public void setProfiles(String[] profiles) {
		this.profiles = profiles;
	}
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public double getUsedMem() {
		return usedMem;
	}
	public void setUsedMem(double usedMem) {
		this.usedMem = usedMem;
	}
	public double getTotalMem() {
		return totalMem;
	}
	public void setTotalMem(double totalMem) {
		this.totalMem = totalMem;
	}
	public double getFreeMem() {
		return freeMem;
	}
	public void setFreeMem(double freeMem) {
		this.freeMem = freeMem;
	}
	public DTime getPingTime() {
		return pingTime;
	}
	public void setPingTime(DTime pingTime) {
		this.pingTime = pingTime;
	} 
	
	
	
	
	
	

}
