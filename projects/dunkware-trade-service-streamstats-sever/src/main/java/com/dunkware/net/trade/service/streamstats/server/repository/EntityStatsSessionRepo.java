package com.dunkware.net.trade.service.streamstats.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntityStatsSessionRepo extends MongoRepository<EntityStatsSessionDoc, Long>  {

}
