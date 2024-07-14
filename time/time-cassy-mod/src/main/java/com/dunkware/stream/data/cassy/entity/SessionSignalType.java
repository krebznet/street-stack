package com.dunkware.stream.data.cassy.entity;

import java.util.Map;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("session_signal_type")
public class SessionSignalType {
    @PrimaryKey
    private SessionSignalTypeKey key;

    private Map<Integer, Double> vars;

    // Getters and setters
}
