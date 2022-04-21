package com.dunkware.net.cluster.node.events;

import com.dunkware.common.util.events.DEvent;

public class DClusterComponentAdded extends DEvent {

	private Object component; 
	
	public DClusterComponentAdded(Object component) { 
		this.component = component; 
	}
	
	public Object getComponent() {
		return component;
	}
}
