package com.dunkware.spring.cluster.protocol;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DDateTime;

public class DunkNetNodePing {

	private String id; 
	private DunkNetNodeDescriptor descriptor;
	private DDateTime pingTime;
	private List<String> profiles = new ArrayList<String>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public DunkNetNodeDescriptor getDescriptor() {
		return descriptor;
	}
	public void setDescriptor(DunkNetNodeDescriptor descriptor) {
		this.descriptor = descriptor;
	}
	public DDateTime getPingTime() {
		return pingTime;
	}
	public void setPingTime(DDateTime pingTime) {
		this.pingTime = pingTime;
	}
	public List<String> getProfiles() {
		return profiles;
	}
	public void setProfiles(List<String> profiles) {
		this.profiles = profiles;
	}
	
	
	
	
	
	
	
	
	
}
