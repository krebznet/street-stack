package com.dunkware.trade.net.service.streamstats.server.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stream_stats_entity_session")
public class EntityStatsSessionDoc {
	
	@Id
	private long uid;
	private LocalDate date;  
	private String stream;  
	private int id; 
	private String ident; 
	
	@Transient
	public static final String SEQUENCE_NAME = "stream_stats_entity_session";
	
	private List<EntityStatsSessionDocVar> vars = new ArrayList<EntityStatsSessionDocVar>();

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
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

	public List<EntityStatsSessionDocVar> getVars() {
		return vars;
	}

	public void setVars(List<EntityStatsSessionDocVar> vars) {
		this.vars = vars;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	
	
	
	
	
	
	
	
	

}
