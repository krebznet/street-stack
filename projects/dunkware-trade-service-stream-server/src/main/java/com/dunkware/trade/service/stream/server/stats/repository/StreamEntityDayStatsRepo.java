package com.dunkware.trade.service.stream.server.stats.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StreamEntityDayStatsRepo
	extends MongoRepository<StreamEntityDayStatsDoc, Long> {
}
