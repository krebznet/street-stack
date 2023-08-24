package com.dunkware.spring.cluster.protocol.descriptors;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.spring.cluster.DunkNetException;

public class DunkNetDescriptors {
	
	private List<DunkNetChannelDescriptor> channels = new ArrayList<DunkNetChannelDescriptor>();
	private List<DunkNetServiceDescriptor> services = new ArrayList<DunkNetServiceDescriptor>();
	private List<DunkNetEventDescriptor> events = new ArrayList<DunkNetEventDescriptor>();
	
	public List<DunkNetChannelDescriptor> getChannels() {
		return channels;
	}
	public void setChannels(List<DunkNetChannelDescriptor> channels) {
		this.channels = channels;
	}
	public List<DunkNetServiceDescriptor> getServices() {
		return services;
	}
	public void setServices(List<DunkNetServiceDescriptor> services) {
		this.services = services;
	}
	public List<DunkNetEventDescriptor> getEvents() {
		return events;
	}
	public void setEvents(List<DunkNetEventDescriptor> events) {
		this.events = events;
	}
	
	@Transient
	public DunkNetChannelDescriptor getChannel(Object input) throws DunkNetException { 
		for (DunkNetChannelDescriptor dunkNetChannelDescriptor : channels) {
			if(dunkNetChannelDescriptor.getInput().equals(input.getClass().getName())) { 
				return dunkNetChannelDescriptor;
			}
		}
		throw new DunkNetException("Descriptor channel payload " + input.getClass().getName() + " not found on source");
	}
	
	@Transient
	public DunkNetServiceDescriptor getService(Object input) throws DunkNetException { 
		for (DunkNetServiceDescriptor dunkNetChannelDescriptor : services) {
			if(dunkNetChannelDescriptor.getInput().equals(input.getClass().getName())) { 
				return dunkNetChannelDescriptor;
			}
		}
		throw new DunkNetException("Descriptor channel payload " + input.getClass().getName() + " not found on source");
	}
	
	
	@Transient
	public boolean hasChannel(Object input) { 
		for (DunkNetChannelDescriptor dunkNetChannelDescriptor : channels) {
			System.out.println(dunkNetChannelDescriptor.getInput());
			System.out.println(input.getClass().getName());
			if(dunkNetChannelDescriptor.getInput().equals(input.getClass().getName())) { 
				return true; 
			}
		}
		return false; 
	}
	
	@Transient
	public boolean hasService(Object input) { 
		for (DunkNetServiceDescriptor service : services) {
			if(service.getInput().equals(input.getClass().getName())) {
				return true;
			}
		}
		return false; 
	}
	
	@Transient
	public boolean hasEvent(Object input) { 
		for (DunkNetEventDescriptor event : events) {
			if(event.getInput().equals(input.getClass().getName())) { 
				return true;
			}
		}
		return false;
	}
	
	

}
