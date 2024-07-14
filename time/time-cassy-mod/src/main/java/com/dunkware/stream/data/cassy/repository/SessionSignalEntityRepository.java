package com.dunkware.stream.data.cassy.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.dunkware.stream.data.cassy.entity.SessionSignalEntity;
import com.dunkware.stream.data.cassy.entity.SessionSignalEntityKey;

public interface SessionSignalEntityRepository extends CassandraRepository<SessionSignalEntity, SessionSignalEntityKey> {
}
