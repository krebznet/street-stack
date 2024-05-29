package com.dunkware.stream.data.lib.stats.entity;

import io.vertx.core.Future;

public interface EntityStatProvider {
	
	
	public Future<EntityStatResp> request(EntityStatReq request);

}
