package com.dunkware.stream.data.cassy.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;

@PrimaryKeyClass
public class SessionSignalTypeKey implements Serializable {
    @PrimaryKeyColumn(name = "signal", type = PrimaryKeyType.PARTITIONED)
    private int signal;

    @PrimaryKeyColumn(name = "stream", type = PrimaryKeyType.PARTITIONED)
    private int stream;

    @PrimaryKeyColumn(name = "date", type = PrimaryKeyType.PARTITIONED)
    private LocalDate date;

    @PrimaryKeyColumn(name = "time", type = PrimaryKeyType.CLUSTERED)
    private LocalTime time;

    @PrimaryKeyColumn(name = "entity", type = PrimaryKeyType.CLUSTERED)
    private int entity;

    // Getters and setters
}
