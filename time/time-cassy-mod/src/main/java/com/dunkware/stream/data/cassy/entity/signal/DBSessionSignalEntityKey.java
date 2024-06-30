package com.dunkware.stream.data.cassy.entity.signal;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyClass
public class DBSessionSignalEntityKey {
	
	@PrimaryKeyColumn(name = "stream", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private int stream;

	@PrimaryKeyColumn(name = "signal", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	private int entity;

	@CassandraType(type = CassandraType.Name.DATE)
	@PrimaryKeyColumn(name = "date", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
	private LocalDate date;
	
	@PrimaryKeyColumn(name = "time", ordinal = 3, type = PrimaryKeyType.CLUSTERED)
    @CassandraType(type = CassandraType.Name.TIME)
	private LocalTime time;
	

}
