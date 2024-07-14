package com.dunkware.stream.data.cassy.entity;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("stream_session")
public class StreamSession {
    @PrimaryKey
    private StreamSessionKey key;

    private List<Integer> vars;
    private List<Integer> entities;
    private List<Integer> stats;
    private List<Integer> signals;
    private LocalTime start;
    private LocalTime stop;
    private double version;

    // Getters and setters
}
