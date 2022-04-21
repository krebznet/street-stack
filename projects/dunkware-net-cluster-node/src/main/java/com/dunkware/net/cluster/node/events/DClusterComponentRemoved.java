package com.dunkware.net.cluster.node.events;

import com.dunkware.common.util.events.DEvent;

public class DClusterComponentRemoved extends DEvent {

	private Object component; 
	
	public DClusterComponentRemoved(Object component) { 
		this.component = component; 
	}
	
	public Object getComponent() {
		return component;
	}
}
