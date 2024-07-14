package com.dunkware.stream.data.cassy.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.dunkware.stream.data.cassy.entity.SessionSignalType;
import com.dunkware.stream.data.cassy.entity.SessionSignalTypeKey;

public interface SessionSignalTypeRepository extends CassandraRepository<SessionSignalType, SessionSignalTypeKey> {
}
