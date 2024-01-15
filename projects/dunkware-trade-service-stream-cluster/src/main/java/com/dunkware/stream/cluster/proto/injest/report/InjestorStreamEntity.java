package com.dunkware.stream.cluster.proto.injest.report;

import java.util.ArrayList;
import java.util.List;

public class InjestorStreamEntity {
	
	private long id; 
	private int todayCount; 
	private List<InjestorStreamVariable> vars = new ArrayList<InjestorStreamVariable>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getTodayCount() {
		return todayCount;
	}
	public void setTodayCount(int todayCount) {
		this.todayCount = todayCount;
	}
	public List<InjestorStreamVariable> getVars() {
		return vars;
	}
	public void setVars(List<InjestorStreamVariable> vars) {
		this.vars = vars;
	}
	
	

}
