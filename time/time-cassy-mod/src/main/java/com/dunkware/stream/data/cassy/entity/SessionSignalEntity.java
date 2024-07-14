package com.dunkware.stream.data.cassy.entity;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("session_signal_entity")
public class SessionSignalEntity {
    @PrimaryKey
    private SessionSignalEntityKey key;

    private Map<Integer, Double> vars;

    // Getters and setters
}
