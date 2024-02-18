package com.dunkware.trade.net.data.server.stream.entitystats.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EntityStatsRepo extends CrudRepository<EntityStatsEnt, Long> {

	
	List<EntityStatsEnt> findByStreamIdent(String streamIdent);
	
}
