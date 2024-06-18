package com.dunkware.xstream.model.stats;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EntityStatsAgg {
	
	private LocalDate start; 
	private LocalDate end; 
	private int id; 
	private String ident; 
	private String stream; 
	private int sessions;
	private int days;
	
	private List<EntityStatsAggVar> vars = new ArrayList<EntityStatsAggVar>();

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public int getSessions() {
		return sessions;
	}

	public void setSessions(int sessions) {
		this.sessions = sessions;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public List<EntityStatsAggVar> getVars() {
		return vars;
	}

	public void setVars(List<EntityStatsAggVar> vars) {
		this.vars = vars;
	}

	
	
	


}
