package com.dunkware.stream.data.cassy.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.dunkware.stream.data.cassy.entity.StreamSession;
import com.dunkware.stream.data.cassy.entity.StreamSessionKey;

public interface StreamSessionRepository extends CassandraRepository<StreamSession, StreamSessionKey> {
}
