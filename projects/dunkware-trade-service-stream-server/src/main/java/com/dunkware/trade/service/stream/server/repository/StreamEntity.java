package com.dunkware.trade.service.stream.server.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.dunkware.common.spec.locale.DCountry;
import com.dunkware.trade.service.stream.json.controller.spec.StreamState;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity(name = "stream_stream")
public class StreamEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	private String name; 
	private String tickerLists;
	private String dataTicks;
	@Enumerated(EnumType.STRING)
	private DCountry country; 
	@Column(columnDefinition = "text")
	private String spec;
	

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval =  true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "stream_id")
	private List<StreamSessionEntity> sessions = new ArrayList<StreamSessionEntity>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval =  true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "stream_id")
	private List<StreamVersionEntity> versions = new ArrayList<StreamVersionEntity>();
	
	private StreamState state = null;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<StreamSessionEntity> getSessions() {
		return sessions;
	}

	public void setSessions(List<StreamSessionEntity> sessions) {
		this.sessions = sessions;
	}

	public List<StreamVersionEntity> getVersions() {
		return versions;
	}

	public void setVersions(List<StreamVersionEntity> versions) {
		this.versions = versions;
	}

	public String getTickerLists() {
		return tickerLists;
	}

	public void setTickerLists(String tickerLists) {
		this.tickerLists = tickerLists;
	}

	public String getDataTicks() {
		return dataTicks;
	}

	public void setDataTicks(String dataTicks) {
		this.dataTicks = dataTicks;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public DCountry getCountry() {
		return country;
	}

	public void setCountry(DCountry country) {
		this.country = country;
	}

	public StreamState getState() {
		return state;
	}

	public void setState(StreamState state) {
		this.state = state;
	}

	
	
	
	
	
	
	
	
	
	

}
