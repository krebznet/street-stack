package com.dunkware.trade.service.stream.server.stats.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.dunkware.common.util.time.DunkTime;
import com.dunkware.xstream.model.stats.EntityStatsSessionVar;

@Document(collection = "stream_stats_entity_day_test")
public class StreamEntityDayStatsDoc {

	public static void main(String[] args) {
		LocalTime time = LocalTime.now();
		String serialized = DunkTime.formatHHMMSS(time);
		System.out.println(serialized);
		LocalTime deser = LocalTime.parse(serialized);
		System.out.println(DunkTime.formatHHMMSS(deser));
	}
	
	@Transient
	public static final String SEQUENCE_NAME = "wow_duncan";
	

	@Id
	private long id;
	private LocalDate date;
	private int entId;
	private String entIdent;
	private String stream;

	private List<EntityStatsSessionVar> vars = new ArrayList<EntityStatsSessionVar>();

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

	public List<EntityStatsSessionVar> getVars() {
		return vars;
	}

	public void setVars(List<EntityStatsSessionVar> vars) {
		this.vars = vars;
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

	public String getEntIdent() {
		return entIdent;
	}

	public void setEntIdent(String entIdent) {
		this.entIdent = entIdent;
	}
	
	
	
	

}
