package com.dunkware.spring.cluster.event;

import com.dunkware.spring.cluster.DunkNetNode;

public class EDunkNetNodeEvent  {

	private DunkNetNode node; 
	
	public EDunkNetNodeEvent(DunkNetNode node) {
		this.node = node;
	}
	
	public DunkNetNode getNode() { 
		return node;
	}
}
