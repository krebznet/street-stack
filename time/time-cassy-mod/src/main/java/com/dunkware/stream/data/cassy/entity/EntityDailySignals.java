package com.dunkware.stream.data.cassy.entity;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.time.Instant;
import java.util.Map;
//TODO: AVINASHANV-10 Cadssandra Enity
/**
 * this how we model entities we include he key class and annotae it with primary key
 */
@Table("entity_daily_signals")
public class EntityDailySignals {
    @PrimaryKey
    private EntityDailySignalsKey key;

    private Map<Integer, Double> vars;

    // Getters and setters
}
