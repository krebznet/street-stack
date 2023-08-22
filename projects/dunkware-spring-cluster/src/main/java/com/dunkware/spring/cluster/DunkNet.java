package com.dunkware.spring.cluster;

import java.util.List;
import java.util.Vector;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.spring.cluster.DunkNetComponent.ComponentMethod;
import com.dunkware.spring.cluster.core.request.DunkNetChannelRequest;
import com.dunkware.spring.cluster.core.request.DunkNetServiceRequest;
import com.dunkware.spring.cluster.protocol.DunkNetNodeDescriptor;

public interface DunkNet {
	
	public DEventNode getEventNode();
	
	public String getId(); 
	
	public void event(Object payplaod) throws DunkNetException;
	
	public DunkNetNodeDescriptor getDescriptor();
	
	public Object serviceBlocking(Object payload) throws DunkNetException;
	
	public DunkNetServiceRequest service(Object payload) throws DunkNetException;
	
	public DunkNetChannelRequest channel(Object payload) throws DunkNetException;
	
	public Vector<DunkNetNode> getNodes();
	
	public Vector<DunkNetNode> getNodes(String...profiles);
	
	public boolean nodeExists(String id);
	
	public DunkNetNode getNode(String id);
	
	public DunkNetBean getBean();
	
	public DunkNetConfig getConfig();
	
	public DExecutor getExecutor(); 
	
	public ComponentMethod getSerivceMethod(Object input) throws DunkNetException; 
	
	public ComponentMethod getChannelMethod(Object input) throws DunkNetException; 
	
	public List<ComponentMethod> getEventMethods(Object input) throws DunkNetException;
	
}

