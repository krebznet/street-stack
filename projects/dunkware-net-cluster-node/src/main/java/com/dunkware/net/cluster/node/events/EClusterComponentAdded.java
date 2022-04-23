package com.dunkware.net.cluster.node.events;

import com.dunkware.common.util.events.DEvent;

public class EClusterComponentAdded extends DEvent {

	private Object component; 
	
	public EClusterComponentAdded(Object component) { 
		this.component = component; 
	}
	
	public Object getComponent() {
		return component;
	}
}
