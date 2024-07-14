package com.dunkware.stream.data.cassy.entity;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.time.Instant;
import java.util.Map;

@Table("entity_daily_signals")
public class EntityDailySignals {
    @PrimaryKey
    private EntityDailySignalsKey key;

    private Map<Integer, Double> vars;

    // Getters and setters
}
