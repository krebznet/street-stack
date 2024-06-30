package com.dunkware.stream.data.cassy.entity.stats;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.time.LocalDate;

@PrimaryKeyClass
public class DBSessionEntityStatsKey {


    @PrimaryKeyColumn(name = "stream", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private int stream;

    @PrimaryKeyColumn(name = "date", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private LocalDate date;

    @PrimaryKeyColumn(name = "entity", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
    private int entity;

    public DBSessionEntityStatsKey(int stream) {
        this.stream = stream;
    }

    public DBSessionEntityStatsKey(int stream, LocalDate date, int entity) {
        this.stream = stream;
        this.date = date;
        this.entity = entity;
    }


    public int getStream() {
        return stream;
    }

    public void setStream(int stream) {
        this.stream = stream;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getEntity() {
        return entity;
    }

    public void setEntity(int entity) {
        this.entity = entity;
    }
}
