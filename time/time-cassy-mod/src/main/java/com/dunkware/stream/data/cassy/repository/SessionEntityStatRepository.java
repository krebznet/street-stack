package com.dunkware.stream.data.cassy.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.dunkware.stream.data.cassy.entity.SessionEntityStat;
import com.dunkware.stream.data.cassy.entity.SessionEntityStatKey;

public interface SessionEntityStatRepository extends CassandraRepository<SessionEntityStat, SessionEntityStatKey> {
}
