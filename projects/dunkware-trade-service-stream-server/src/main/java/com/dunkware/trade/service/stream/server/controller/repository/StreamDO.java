package com.dunkware.trade.service.stream.server.controller.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.dunkware.common.spec.locale.DCountry;
import com.dunkware.trade.service.stream.json.controller.spec.StreamState;
import com.dunkware.trade.service.stream.server.session.repository.StreamSessionDO;

@Entity(name = "stream_stream")
public class StreamDO {
	
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
	private List<StreamSessionDO> sessions = new ArrayList<StreamSessionDO>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval =  true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "stream_id")
	private List<StreamVersionDO> versions = new ArrayList<StreamVersionDO>();
	
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

	public List<StreamSessionDO> getSessions() {
		return sessions;
	}

	public void setSessions(List<StreamSessionDO> sessions) {
		this.sessions = sessions;
	}

	public List<StreamVersionDO> getVersions() {
		return versions;
	}

	public void setVersions(List<StreamVersionDO> versions) {
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
