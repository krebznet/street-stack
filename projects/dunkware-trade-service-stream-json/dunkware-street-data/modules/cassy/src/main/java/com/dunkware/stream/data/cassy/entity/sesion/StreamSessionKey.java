package com.dunkware.stream.data.cassy.entity.sesion;

import java.time.LocalDate;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class StreamSessionKey {
	
    @PrimaryKeyColumn(name = "stream", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private int stream;
    
  // StreamSessiosModel
    	// StreamSession
    @CassandraType(type = CassandraType.Name.DATE)
    @PrimaryKeyColumn(name = "date", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private LocalDate date;

    public StreamSessionKey() {
    }

    public StreamSessionKey(int stream, LocalDate date) {
        this.stream = stream;
        this.date = date;
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
}

