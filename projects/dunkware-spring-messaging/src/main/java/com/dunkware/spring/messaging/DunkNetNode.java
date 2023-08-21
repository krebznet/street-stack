package com.dunkware.spring.messaging;

import com.dunkware.spring.messaging.core.request.DunkNetChannelRequest;
import com.dunkware.spring.messaging.core.request.DunkNetServiceRequest;
import com.dunkware.spring.messaging.message.DunkNetMessage;
import com.dunkware.spring.messaging.message.DunkNetMessageTransport;
import com.dunkware.spring.messaging.protocol.DunkNetNodePing;
import com.dunkware.spring.messaging.protocol.DunkNetNodeService;

public interface DunkNetNode {

	String getId();
	
	public DunkNet getDunkNet(); 
	
	public void ping(DunkNetNodePing descriptor); 
	
	public void send(DunkNetMessage message) throws DunkNetException;
	
	void send(DunkNetMessageTransport transport) throws DunkNetException;
	
	void event(Object payload) throws DunkNetException;

	DunkNetServiceRequest service(Object payload) throws DunkNetException;

	DunkNetChannelRequest channel(Object payload) throws DunkNetException;
	
	public boolean isChannelProvider(Object input);
	
	public boolean isEventConsumer(Object input);
	
	public boolean isServiceProvider(Object input);
	
	DunkNetNodeService getServiceDescriptor(Object input); 
	
	public Class<?> getServiceReturnType(Object input) throws DunkNetException;
	
	public Class<?> getChannelReturnType(Object intput) throws DunkNetException;

	public boolean hasProfile(String profile); 
}


