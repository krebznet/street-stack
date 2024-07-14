package com.dunkware.stream.data.cassy.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.dunkware.stream.data.cassy.entity.SessionEntityStats;
import com.dunkware.stream.data.cassy.entity.SessionEntityStatsKey;

public interface SessionEntityStatsRepository extends CassandraRepository<SessionEntityStats, SessionEntityStatsKey> {
}
