package com.dunkware.net.cluster.node.cluster;

import com.dunkware.net.cluster.node.event.DNetEventService;
import com.dunkware.net.cluster.node.search.DNetSearchService;

public interface DNetClusterService {

	public DNetSearchService searchService();
	
	public DNetEventService eventService();
	
	// managed componenet
	
}

