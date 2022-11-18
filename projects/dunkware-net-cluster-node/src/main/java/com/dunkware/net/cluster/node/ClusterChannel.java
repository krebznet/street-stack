package com.dunkware.net.cluster.node;

import com.dunkware.spring.channel.Channel;

public interface ClusterChannel extends Channel {
	
	
	ClusterNode getNode();
	
	
	

}
