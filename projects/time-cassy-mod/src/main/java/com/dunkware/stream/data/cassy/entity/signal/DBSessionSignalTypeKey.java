package com.dunkware.stream.data.cassy.entity.signal;

import java.time.LocalDate;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

//@PrimaryKeyClass
public class DBSessionSignalTypeKey {
	
	@PrimaryKeyColumn(name = "stream", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private int stream;

	@PrimaryKeyColumn(name = "signal", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	private int entity;

	@CassandraType(type = CassandraType.Name.DATE)
	@PrimaryKeyColumn(name = "date", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
	private LocalDate date;

}
