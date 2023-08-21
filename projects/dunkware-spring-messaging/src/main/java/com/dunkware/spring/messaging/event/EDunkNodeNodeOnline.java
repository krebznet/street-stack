package com.dunkware.spring.messaging.event;

import com.dunkware.spring.messaging.DunkNetNode;

public class EDunkNodeNodeOnline extends EDunkNetNodeEvent {

	public EDunkNodeNodeOnline(DunkNetNode node) {
		super(node);
	}

	
}
