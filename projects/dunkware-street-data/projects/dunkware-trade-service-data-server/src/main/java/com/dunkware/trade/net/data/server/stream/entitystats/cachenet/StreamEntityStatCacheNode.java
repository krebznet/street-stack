package com.dunkware.trade.net.data.server.stream.entitystats.cachenet;

import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.DunkNetNode;

public interface StreamEntityStatCacheNode {

	DunkNetNode getNode();
	
	boolean hasEntity(int id); 
	
	DunkNetChannel getChannel(); 
	
	public void init(Object input);
	
	


}
