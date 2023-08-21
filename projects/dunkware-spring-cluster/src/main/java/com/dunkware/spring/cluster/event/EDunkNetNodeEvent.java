package com.dunkware.spring.cluster.event;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.spring.cluster.DunkNetNode;

public class EDunkNetNodeEvent extends DEvent  {

	private DunkNetNode node; 
	
	public EDunkNetNodeEvent(DunkNetNode node) {
		this.node = node;
	}
	
	public DunkNetNode getNode() { 
		return node;
	}
}
