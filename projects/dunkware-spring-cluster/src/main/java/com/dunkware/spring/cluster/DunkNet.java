package com.dunkware.spring.cluster;

import java.util.Vector;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.spring.cluster.core.controllers.DunkNetController;
import com.dunkware.spring.cluster.core.controllers.DunkNetState;
import com.dunkware.spring.cluster.core.request.DunkNetChannelRequest;
import com.dunkware.spring.cluster.core.request.DunkNetServiceRequest;
import com.dunkware.spring.cluster.protocol.descriptors.DunkNetNodeDescriptor;

public interface DunkNet {
	
	public DEventNode getEventNode();
	
	public String getId(); 
	
	public void event(Object payplaod) throws DunkNetException;
	
	public DunkNetNodeDescriptor getDescriptor();
	
	public Object serviceBlocking(Object payload) throws DunkNetException;
	
	public DunkNetServiceResponse serviceFuure(Object payload) throws DunkNetException;
	
	public DunkNetServiceRequest service(Object payload) throws DunkNetException;
	
	public DunkNetChannelRequest channel(Object payload) throws DunkNetException;
	
	public Vector<DunkNetNode> getNodes();
	
	public Vector<DunkNetNode> getNodes(String...profiles);
	
	public boolean nodeExists(String id);
	
	public DunkNetNode getNode(String id);
	
	public DunkNetBean getBean();
	
	public DunkNetConfig getConfig();
	
	public DExecutor getExecutor(); 
	
	public DunkNetExtensions extensions();
	
	public DunkNetState getState();
	
	public DunkNetController getController();
	
}

