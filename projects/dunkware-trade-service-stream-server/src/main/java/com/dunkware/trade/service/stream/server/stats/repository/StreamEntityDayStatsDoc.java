package com.dunkware.trade.service.stream.server.stats.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.dunkware.xstream.model.stats.StreamSignalStats;
import com.dunkware.xstream.model.stats.StreamVariableStats;

@Document(collection = "stream_stats_entity_day")
public class StreamEntityDayStatsDoc {

	@Transient
	public static final String SEQUENCE_NAME = "entity_day_stats";

	@Id
	private long id;
	private LocalDate date;
	private int entId;
	private String stream;

	private List<StreamVariableStats> vars = new ArrayList<StreamVariableStats>();
	private List<StreamSignalStats> sigs = new ArrayList<StreamSignalStats>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<StreamVariableStats> getVars() {
		return vars;
	}

	public void setVars(List<StreamVariableStats> vars) {
		this.vars = vars;
	}

	public List<StreamSignalStats> getSigs() {
		return sigs;
	}

	public void setSigs(List<StreamSignalStats> sigs) {
		this.sigs = sigs;
	}

	public int getEntId() {
		return entId;
	}

	public void setEntId(int entId) {
		this.entId = entId;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}
	
	

}
