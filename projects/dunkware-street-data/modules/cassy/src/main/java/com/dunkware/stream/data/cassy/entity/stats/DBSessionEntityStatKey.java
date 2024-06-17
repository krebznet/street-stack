package com.dunkware.stream.data.cassy.entity.stats;

import java.time.LocalDate;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class DBSessionEntityStatKey {

    @PrimaryKeyColumn(name = "stream", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private int stream;

    @PrimaryKeyColumn(name = "date", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private LocalDate date;

    @PrimaryKeyColumn(name = "entity", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
    private int entity;

    @PrimaryKeyColumn(name = "stat", ordinal = 3, type = PrimaryKeyType.CLUSTERED)
    private int stat;

    public DBSessionEntityStatKey() {

    }

    public DBSessionEntityStatKey(int stream, LocalDate date, int entity, int stat) {
        this.stream = stream;
        this.date = date;
        this.entity = entity;
        this.stat = stat;
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

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }
}
