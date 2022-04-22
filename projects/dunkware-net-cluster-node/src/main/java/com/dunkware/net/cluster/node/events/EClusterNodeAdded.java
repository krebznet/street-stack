package com.dunkware.net.cluster.node.events;

import com.dunkware.common.util.events.DEvent;

public class EClusterNodeAdded extends DEvent {

	private Object component; 
	
	public EClusterNodeAdded(Object component) { 
		this.component = component; 
	}
	
	public Object getComponent() {
		return component;
	}
}
