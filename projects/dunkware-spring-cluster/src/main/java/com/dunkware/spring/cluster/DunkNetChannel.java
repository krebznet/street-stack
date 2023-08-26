package com.dunkware.spring.cluster;

import java.util.List;

import com.dunkware.spring.cluster.core.request.DunkNetChannelRequest;
import com.dunkware.spring.cluster.core.request.DunkNetServiceRequest;
import com.dunkware.spring.cluster.message.DunkNetMessage;
import com.dunkware.spring.cluster.protocol.descriptors.DunkNetDescriptors;

public interface DunkNetChannel {
	
	void addExtension(Object source) throws DunkNetException;
	
	void closeChannel();
	
	String getLabel();
	
	boolean isOpen();
	
	Throwable getException();
	
	DunkNetChannelState getState();
	
	public void setState(DunkNetChannelState state);
	
	public void setRemoteDescriptors(DunkNetDescriptors descriptors);
	
	public DunkNetDescriptors getRemoteDescriptors();
	
	public void setDescriptors(DunkNetDescriptors descriptors);
	
	DunkNetChannelRequest channel(Object payload) throws Exception; 
		
	void event(Object payload) throws DunkNetException; 
	
	public DunkNetServiceRequest service(Object payload) throws DunkNetException;
	
	public Object serviceBlocking(Object payload) throws DunkNetException;
	
	public void addListener(DunkNetChannelListener listener);
	
	public void removeListener(DunkNetChannelListener listener);
	
	public DunkNetNode getNode();
	
	String getChannelId();
	
	List<DunkNetChannel> subChannels();
	
	public DunkNetDescriptors getDescriptors();
	
	public DunkNetExtensions getExtensions();
	
	public void setHandler(DunkNetChannelHandler handler) throws DunkNetException;
	
	DunkNetChannelHandler getHandler();

	
	
}
