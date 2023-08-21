package com.dunkware.trade.service.stream.server.stats.repository;

import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface EntityStatsSessionRepo extends MongoRepository<EntityStatsSessionDoc, Long>  {

	@Query(collation = "poopy")
	public Stream<EntityStatsSessionDoc> streamAll();
}
