package com.dunkware.stream.data.cassy.entity;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyClass
public class SessionEntityStatKey implements Serializable {
    @PrimaryKeyColumn(name = "stream", type = PrimaryKeyType.PARTITIONED)
    private int stream;

    @PrimaryKeyColumn(name = "date", type = PrimaryKeyType.PARTITIONED)
    private LocalDate date;

    @PrimaryKeyColumn(name = "entity", type = PrimaryKeyType.CLUSTERED)
    private int entity;

  

    // Getters and setters
}
