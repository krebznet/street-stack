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
public class StreamSessionKey implements Serializable {
    @PrimaryKeyColumn(name = "stream", type = PrimaryKeyType.PARTITIONED)
    private int stream;

    @PrimaryKeyColumn(name = "date", type = PrimaryKeyType.CLUSTERED)
    private LocalDate date;

    // Getters and setters
}
