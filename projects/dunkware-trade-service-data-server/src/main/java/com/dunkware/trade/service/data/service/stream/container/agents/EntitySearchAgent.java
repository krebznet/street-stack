package com.dunkware.trade.service.data.service.stream.container.agents;

import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.net.proto.data.cluster.GContainerWorkersMessage;
import com.dunkware.net.proto.data.cluster.GEntitySearchRequest;
import com.dunkware.net.proto.netstream.GEntityMatcher;
import com.dunkware.trade.service.data.service.stream.container.StreamContainerController;

public class EntitySearchAgent  {

	private int searchId;
	private StreamContainerController cluster;
	
	public EntitySearchAgent(GEntityMatcher matcher, StreamContainerController cluster) {
		searchId = DRandom.getRandom(1, 40404040);
		this.cluster = cluster;
		GContainerWorkersMessage message = GContainerWorkersMessage.newBuilder().setEntitySearchRequest(GEntitySearchRequest.newBuilder().setMatcher(matcher).setSearchId(searchId).build()).build();
		// okay at this point we want to have callback; 
		
		
	}
	
}
