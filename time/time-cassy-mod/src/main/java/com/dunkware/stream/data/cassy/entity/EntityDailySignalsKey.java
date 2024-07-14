package com.dunkware.stream.data.cassy.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Instant;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;

@PrimaryKeyClass
public class EntityDailySignalsKey implements Serializable {
    @PrimaryKeyColumn(name = "entity_id", type = PrimaryKeyType.PARTITIONED)
    private int entityId;

    @PrimaryKeyColumn(name = "signal_date", type = PrimaryKeyType.PARTITIONED)
    private LocalDate signalDate;

    @PrimaryKeyColumn(name = "stream_id", type = PrimaryKeyType.PARTITIONED)
    private int streamId;
    
    @PrimaryKeyColumn(name = "signal_time", type = PrimaryKeyType.CLUSTERED)
    private Instant signalTime;

    @PrimaryKeyColumn(name = "signal_id", type = PrimaryKeyType.CLUSTERED)
    private int signalId;



    // Getters and setters
}
