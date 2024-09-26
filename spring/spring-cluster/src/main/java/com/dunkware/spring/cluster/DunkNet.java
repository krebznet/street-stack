package com.dunkware.spring.cluster;

import java.util.Vector;

import org.apache.kafka.clients.producer.Producer;

import com.dunkware.spring.cluster.core.controllers.DunkNetController;
import com.dunkware.spring.cluster.core.controllers.DunkNetState;
import com.dunkware.spring.cluster.core.request.DunkNetChannelRequest;
import com.dunkware.spring.cluster.core.request.DunkNetServiceRequest;
import com.dunkware.spring.cluster.protocol.descriptors.DunkNetNodeDescriptor;
import com.dunkware.utils.core.concurrent.DunkExecutor;
import com.dunkware.utils.core.event.EventNode;
import com.dunkware.utils.kafka.admin.DunkKafkaAdmin;

import io.vertx.core.Future;

public interface DunkNet {
	
	public EventNode getEventNode();
	
	public String getId();
	
	public DunkKafkaAdmin createAdminClient() throws DunkNetException;
	
	public void extension(Object extension) throws DunkNetException;
	
	public void event(Object payplaod) throws DunkNetException;
	
	public DunkNetNodeDescriptor getDescriptor();
	
	public Object serviceBlocking(Object payload) throws DunkNetException;
	
	public Future<Object> serviceFuure(Object payload);
	
	public int getNodeCount();
	
	public DunkNetServiceRequest service(Object payload) throws DunkNetException;
	
	public DunkNetChannelRequest channel(Object payload) throws DunkNetException;
	
	public Vector<DunkNetNode> getNodes();
	
	public Vector<DunkNetNode> getNodes(String...profiles);
	
	public boolean nodeExists(String id);
	
	public DunkNetNode getNode(String id);
	
	public DunkNetBean getBean();
	
	public DunkNetConfig getConfig();
	
	public DunkExecutor getExecutor(); 
	
	public DunkNetExtensions extensions();
	
	public DunkNetState getState();
	
	public DunkNetController getController();
	
	public Producer<Integer, byte[]> getKafkaProducer();
	
}

