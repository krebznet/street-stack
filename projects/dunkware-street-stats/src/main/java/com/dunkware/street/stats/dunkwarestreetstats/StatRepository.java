package com.dunkware.street.stats.dunkwarestreetstats;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StatRepository extends MongoRepository<Stats, String> {
    Optional<Stats> findStatsByName(String name);
}
