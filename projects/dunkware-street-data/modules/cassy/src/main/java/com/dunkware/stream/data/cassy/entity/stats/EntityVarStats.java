package com.dunkware.stream.data.cassy.entity.stats;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@UserDefinedType("entity_var_stats")
public class EntityVarStats {

	@CassandraType(type = Name.MAP,typeArguments = { Name.INT,Name.DOUBLE}) 
	private Map<Integer,Double> stats = new HashMap<Integer,Double>();

	@CassandraType(type = Name.MAP,typeArguments = { Name.INT,Name.TIME})
	private Map<Integer,LocalTime> times = new HashMap<Integer,LocalTime>();

	public EntityVarStats() {
		
	}

	public EntityVarStats(Map<Integer, Double> stats, Map<Integer, LocalTime> times) {
		this.stats = stats;
		this.times = times;
	}

	public Map<Integer, Double> getStats() {
		return stats;
	}

	public void setStats(Map<Integer, Double> stats) {
		this.stats = stats;
	}

	public Map<Integer, LocalTime> getTimes() {
		return times;
	}

	public void setTimes(Map<Integer, LocalTime> times) {
		this.times = times;
	}
	
	

	
	
}
