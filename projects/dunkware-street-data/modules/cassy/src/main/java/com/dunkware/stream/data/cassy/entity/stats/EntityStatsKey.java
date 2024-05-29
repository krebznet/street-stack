package com.dunkware.stream.data.cassy.entity.stats;

import java.time.LocalDate;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class EntityStatsKey {

    @PrimaryKeyColumn(name = "stream", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private int stream;


    @PrimaryKeyColumn(name = "entity", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private int entity;

    @CassandraType(type = CassandraType.Name.DATE)
    @PrimaryKeyColumn(name = "date", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
    private LocalDate date;


    public EntityStatsKey()  {

    }

    public EntityStatsKey(int stream, int entity, LocalDate date) {
        this.stream = stream;
        this.entity = entity;
        this.date = date;
    }

    public int getStream() {
        return stream;
    }

    public void setStream(int stream) {
        this.stream = stream;
    }

    public int getEntity() {
        return entity;
    }

    public void setEntity(int entity) {
        this.entity = entity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
