package com.dunkware.net.cluster.node.internal;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dunkware.net.cluster.node.events.EClusterComponentAdded;

@Service
public class ClusterRegistry {

	private List<Object> registeredComponents = new ArrayList<Object>();
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private List<ChannelServiceRegistry> channelServices  = new ArrayList<ClusterRegistry.ChannelServiceRegistry>();
	private List<RequestServiceRegistry> requestServices = new ArrayList<ClusterRegistry.RequestServiceRegistry>();
	
	private ClusterImpl cluster;
	
	public void start(ClusterImpl cluster)   { 
		this.cluster = cluster; 
		
	}
	public void addComponent(Object component)  {
		registeredComponents.add(component);
		if(logger.isTraceEnabled()) { 
			logger.trace("Added Component Class " + component.getClass().getName());
		}
		cluster.getEventTree().getRoot().event(new EClusterComponentAdded(component));
		
	}
	

	
	public void removeComponent(Object component) { 
		
	}
	
	private class RequestServiceRegistry  { 
		private String endpoint; 
		private Class clazz;
		public String getEndpoint() {
			return endpoint;
		}
		public void setEndpoint(String endpoint) {
			this.endpoint = endpoint;
		}
		public Class getClazz() {
			return clazz;
		}
		public void setClazz(Class clazz) {
			this.clazz = clazz;
		} 
	
	}
	
	private class ChannelServiceRegistry { 
		private String endpoint; 
		private Class clazz;
		public String getEndpoint() {
			return endpoint;
		}
		public void setEndpoint(String endpoint) {
			this.endpoint = endpoint;
		}
		public Class getClazz() {
			return clazz;
		}
		public void setClazz(Class clazz) {
			this.clazz = clazz;
		} 
	
	}
	
	
}
