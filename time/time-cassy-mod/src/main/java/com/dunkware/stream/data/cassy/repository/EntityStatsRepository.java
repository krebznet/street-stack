package com.dunkware.stream.data.cassy.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.dunkware.stream.data.cassy.entity.EntityStats;
import com.dunkware.stream.data.cassy.entity.EntityStatsKey;

public interface EntityStatsRepository extends CassandraRepository<EntityStats, EntityStatsKey> {


}
