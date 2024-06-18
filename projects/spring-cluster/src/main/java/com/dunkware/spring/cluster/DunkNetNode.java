package com.dunkware.spring.cluster;

import com.dunkware.spring.cluster.core.request.DunkNetChannelRequest;
import com.dunkware.spring.cluster.core.request.DunkNetServiceRequest;
import com.dunkware.spring.cluster.message.DunkNetMessage;
import com.dunkware.spring.cluster.message.DunkNetMessageTransport;
import com.dunkware.spring.cluster.protocol.descriptors.DunkNetNodeDescriptor;

public interface DunkNetNode {

	String getId();
	
	public DunkNet getNet();
	
	public void nodeUpdate(DunkNetNodeDescriptor descriptor);
	
	public DunkNetNodeDescriptor getDescriptor();
	
	public void message(DunkNetMessage message) throws DunkNetException;
	
	void message(DunkNetMessageTransport transport) throws DunkNetException;
	
	void event(Object payload) throws DunkNetException;

	DunkNetServiceRequest service(Object payload) throws DunkNetException;

	DunkNetChannelRequest channel(Object payload) throws DunkNetException;
	
	public DunkNetNodeDescriptor descriptor();
	
	public boolean isOnline(); 
	
	
}


