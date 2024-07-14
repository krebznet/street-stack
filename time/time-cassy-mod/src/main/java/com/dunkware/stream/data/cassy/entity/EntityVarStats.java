package com.dunkware.stream.data.cassy.entity;

import java.time.LocalTime;
import java.util.Map;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@UserDefinedType("entity_var_stats")
public class EntityVarStats {
    private Map<Integer, Double> stats;
    private Map<Integer, LocalTime> times;

}
