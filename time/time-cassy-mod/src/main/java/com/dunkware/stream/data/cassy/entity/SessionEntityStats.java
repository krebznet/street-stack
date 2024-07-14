package com.dunkware.stream.data.cassy.entity;

import java.util.Map;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("session_entity_stats")
public class SessionEntityStats {
    @PrimaryKey
    private SessionEntityStatsKey key;

    private Map<Integer, Integer> signals;
    private Map<Integer, EntityVarStats> vars;

    // Getters and setters
}
