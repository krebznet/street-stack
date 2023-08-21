package com.dunkware.spring.messaging.protocol;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DunkNetNodePing {

	private String id; 
	private String nodeTopic; 
	private LocalDate pingTime;
	
	private List<DunkNetNodeChannel> channels = new ArrayList<DunkNetNodeChannel>();
	private List<DunkNetNodeEvent> messages = new ArrayList<DunkNetNodeEvent>();
	private List<DunkNetNodeService> services = new ArrayList<DunkNetNodeService>();
	
	private List<String> profiles = new ArrayList<String>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNodeTopic() {
		return nodeTopic;
	}
	public void setNodeTopic(String nodeTopic) {
		this.nodeTopic = nodeTopic;
	}
	public LocalDate getPingTime() {
		return pingTime;
	}
	public void setPingTime(LocalDate pingTime) {
		this.pingTime = pingTime;
	}
	public List<DunkNetNodeChannel> getChannels() {
		return channels;
	}
	public void setChannels(List<DunkNetNodeChannel> channels) {
		this.channels = channels;
	}
	public List<DunkNetNodeEvent> getMessages() {
		return messages;
	}
	public void setMessages(List<DunkNetNodeEvent> messages) {
		this.messages = messages;
	}
	public List<DunkNetNodeService> getServices() {
		return services;
	}
	public void setServices(List<DunkNetNodeService> services) {
		this.services = services;
	}
	public List<String> getProfiles() {
		return profiles;
	}
	public void setProfiles(List<String> profiles) {
		this.profiles = profiles;
	}
	
	
	
	
	
	
	
	
	
}
