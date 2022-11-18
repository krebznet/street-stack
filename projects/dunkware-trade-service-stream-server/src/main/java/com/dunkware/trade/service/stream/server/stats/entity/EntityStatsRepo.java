package com.dunkware.trade.service.stream.server.stats.entity;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntityStatsRepo extends MongoRepository<EntityStatsDO, Long> {
	
	

}
