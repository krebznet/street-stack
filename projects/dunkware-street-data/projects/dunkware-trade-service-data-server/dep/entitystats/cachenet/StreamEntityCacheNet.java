package com.dunkware.trade.net.data.server.stream.entitystats.cachenet;

import com.dunkware.trade.service.data.model.entitystats.EntityStatAggRequest;
import com.dunkware.trade.service.data.model.entitystats.EntityStatAggResponse;

public interface StreamEntityCacheNet {
	
	
	public EntityStatAggResponse aggRequest(EntityStatAggRequest request); 
	
	

}
