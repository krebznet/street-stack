package com.dunkware.spring.messaging;

import java.util.Vector;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.spring.messaging.core.request.DunkNetChannelRequest;
import com.dunkware.spring.messaging.core.request.DunkNetServiceRequest;

public interface DunkNet {
	
	public DEventNode getEventNode();
	
	public String getId(); 
	
	public void start(DunkNetConfig props) throws DunkNetException;
	
	public void event(Object payplaod) throws DunkNetException;
	
	public DunkNetServiceRequest service(Object payload) throws DunkNetException;
	
	public DunkNetChannelRequest channel(Object payload) throws DunkNetException;
	
	public Vector<DunkNetNode> getNodes();
	
	public Vector<DunkNetNode> getNodes(String...profiles);
	
	public boolean nodeExists(String id);
	
	public DunkNetNode getNode(String id);
	
	public DunkNetBean getBean();
	
	public DunkNetConfig getConfig();
	
	public DExecutor getExecutor(); 
	
}

