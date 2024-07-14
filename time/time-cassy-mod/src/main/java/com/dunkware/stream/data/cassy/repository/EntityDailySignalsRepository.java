package com.dunkware.stream.data.cassy.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.dunkware.stream.data.cassy.entity.EntityDailySignals;
import com.dunkware.stream.data.cassy.entity.EntityDailySignalsKey;

public interface EntityDailySignalsRepository extends CassandraRepository<EntityDailySignals, EntityDailySignalsKey> {


}
