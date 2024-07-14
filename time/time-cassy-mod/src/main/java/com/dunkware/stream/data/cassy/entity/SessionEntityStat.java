package com.dunkware.stream.data.cassy.entity;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.repository.NoRepositoryBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("session_entity_stat")
public class SessionEntityStat {
    @PrimaryKey
    private SessionEntityStatKey key;

    private double value;
    private LocalTime time;
    
   
    private int element;

 
    private int stat;

    // Getters and setters
}
