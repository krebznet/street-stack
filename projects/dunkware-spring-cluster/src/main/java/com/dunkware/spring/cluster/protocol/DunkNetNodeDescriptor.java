package com.dunkware.spring.cluster.protocol;

import java.util.ArrayList;
import java.util.List;

public class DunkNetNodeDescriptor {
	
	private List<DunkNetNodeChannel> channels = new ArrayList<DunkNetNodeChannel>();
	private List<DunkNetNodeService> services = new ArrayList<DunkNetNodeService>();
	private List<DunkNetNodeEvent> events = new ArrayList<DunkNetNodeEvent>();
	private List<String> profiles = new ArrayList<String>();
	
	public List<DunkNetNodeChannel> getChannels() {
		return channels;
	}
	public void setChannels(List<DunkNetNodeChannel> channels) {
		this.channels = channels;
	}
	public List<DunkNetNodeService> getServices() {
		return services;
	}
	public void setServices(List<DunkNetNodeService> services) {
		this.services = services;
	}
	public List<DunkNetNodeEvent> getEvents() {
		return events;
	}
	public void setEvents(List<DunkNetNodeEvent> events) {
		this.events = events;
	}
	public List<String> getProfiles() {
		return profiles;
	}
	public void setProfiles(List<String> profiles) {
		this.profiles = profiles;
	}
	
	
	
	

}
