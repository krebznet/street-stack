package com.dunkware.stream.data.cassy.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class SessionSignalEntityKey implements Serializable {
    @PrimaryKeyColumn(name = "entity", type = PrimaryKeyType.PARTITIONED)
    private int entity;

    @PrimaryKeyColumn(name = "stream", type = PrimaryKeyType.PARTITIONED)
    private int stream;

    @PrimaryKeyColumn(name = "date", type = PrimaryKeyType.PARTITIONED)
    private LocalDate date;

    @PrimaryKeyColumn(name = "time", type = PrimaryKeyType.CLUSTERED)
    private LocalTime time;

    // Getters and setters
}
