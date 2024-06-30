package com.dunkware.stream.data.cassy.support;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface CustomCassandraRepository<T, ID> extends CassandraRepository<T, ID> {

	<S extends T> Optional<S> insertIfNotExists(S entity);

}
