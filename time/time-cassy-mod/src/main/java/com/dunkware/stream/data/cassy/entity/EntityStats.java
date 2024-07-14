package com.dunkware.stream.data.cassy.entity;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table("entity_stats")
public class EntityStats {
    @PrimaryKey
    private EntityStatsKey key;

    private Map<Integer, Integer> signals;
    private Map<Integer, EntityVarStats> vars;

    // Getters and setters
}
