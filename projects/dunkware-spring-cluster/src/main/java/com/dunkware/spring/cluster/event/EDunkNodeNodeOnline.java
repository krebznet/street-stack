package com.dunkware.spring.cluster.event;

import com.dunkware.spring.cluster.DunkNetNode;

public class EDunkNodeNodeOnline extends EDunkNetNodeEvent {

	public EDunkNodeNodeOnline(DunkNetNode node) {
		super(node);
	}

	
}
