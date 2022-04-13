package com.dunkware.trade.service.stream.json.cluster.spec;

import com.dunkware.common.util.dtime.DTime;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ClusterNodeStatsSpec {

	private String endpoint; 
	private String id; 
	private String[] profiles;
	private ClusterNodeStatus status;
	private DTime lastPing; 
	private String usedMem;
	private String freeMem;
	private String totalMem; 
	
	// cpu and memory would be nice

	public ClusterNodeStatsSpec() { 
		
	}
	
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	
	
	public ClusterNodeStatus getStatus() {
		return status;
	}
	public void setStatus(ClusterNodeStatus status) {
		this.status = status;
	}


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

	public DTime getLastPing() {
		return lastPing;
	}

	public void setLastPing(DTime lastPing) {
		this.lastPing = lastPing;
	}

	public String getTotalMem() {
		return totalMem;
	}

	public void setTotalMem(String totalMem) {
		this.totalMem = totalMem;
	}

	public void setUsedMem(String usedMem) {
		this.usedMem = usedMem;
	}

	public void setFreeMem(String freeMem) {
		this.freeMem = freeMem;
	}

	public String getUsedMem() {
		return usedMem;
	}

	public String getFreeMem() {
		return freeMem;
	}
	
	

	


	
	



	
	


	
	
	
	
	
	
	
}
