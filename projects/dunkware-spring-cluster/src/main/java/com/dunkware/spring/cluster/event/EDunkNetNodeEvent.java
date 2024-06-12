package com.dunkware.spring.cluster.event;

import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.utils.core.events.DunkEvent;

public class EDunkNetNodeEvent extends DunkEvent  {

	private DunkNetNode node; 
	
	public EDunkNetNodeEvent(DunkNetNode node) {
		this.node = node;
	}
	
	public DunkNetNode getNode() { 
		return node;
	}
}
