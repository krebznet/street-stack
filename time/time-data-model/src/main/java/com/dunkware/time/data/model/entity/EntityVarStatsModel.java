package com.dunkware.time.data.model.entity;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class EntityVarStatsModel {

	private int var; 
	private Map<Integer,Double> stats = new HashMap<Integer,Double>();
	private Map<Integer,LocalTime> times = new HashMap<Integer,LocalTime>();
	
	public int getVar() {
		return var;
	}
	public void setVar(int var) {
		this.var = var;
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

