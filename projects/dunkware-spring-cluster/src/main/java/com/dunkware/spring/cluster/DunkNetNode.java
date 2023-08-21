package com.dunkware.spring.cluster;

import com.dunkware.spring.cluster.core.request.DunkNetChannelRequest;
import com.dunkware.spring.cluster.core.request.DunkNetServiceRequest;
import com.dunkware.spring.cluster.message.DunkNetMessage;
import com.dunkware.spring.cluster.message.DunkNetMessageTransport;
import com.dunkware.spring.cluster.protocol.DunkNetNodeChannel;
import com.dunkware.spring.cluster.protocol.DunkNetNodeDescriptor;
import com.dunkware.spring.cluster.protocol.DunkNetNodePing;
import com.dunkware.spring.cluster.protocol.DunkNetNodeService;

public interface DunkNetNode {

	String getId();
	
	public DunkNet getDunkNet(); 
	
	public void ping(DunkNetNodePing ping);
	
	public DunkNetNodeDescriptor getDescriptor();
	
	public void message(DunkNetMessage message) throws DunkNetException;
	
	void message(DunkNetMessageTransport transport) throws DunkNetException;
	
	void event(Object payload) throws DunkNetException;

	DunkNetServiceRequest service(Object payload) throws DunkNetException;

	DunkNetChannelRequest channel(Object payload) throws DunkNetException;
	
	public boolean isChannelProvider(Object input);
	
	public boolean isEventConsumer(Object input);
	
	public boolean isServiceProvider(Object input);
	
	public DunkNetNodeService getServiceDescriptor(Object input) throws DunkNetException;
	
	public DunkNetNodeChannel getChannelDescriptor(Object input) throws DunkNetException;
	
	public boolean hasProfile(String profile); 
}


