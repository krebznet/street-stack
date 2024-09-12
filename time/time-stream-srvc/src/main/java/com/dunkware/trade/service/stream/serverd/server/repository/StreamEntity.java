package com.dunkware.trade.service.stream.serverd.server.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;

@Entity(name = "stream_stream")
public class StreamEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 

	private String name; 

	
	@Column(columnDefinition = "text")
	private String settings;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval =  true)
	@JoinColumn(name = "stream_id")
	private List<StreamSessionEntity> sessions = new ArrayList<StreamSessionEntity>();

	@OrderColumn(name = "depluy_timestamp")
	private LocalDateTime deployTimestamp;
	
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

	public String getSettings() {
		return settings;
	}

	public void setSettings(String settings) {
		this.settings = settings;
	}

	public LocalDateTime getDeployTimestamp() {
		return deployTimestamp;
	}

	public void setDeployTimestamp(LocalDateTime deployTimestamp) {
		this.deployTimestamp = deployTimestamp;
	}
	
	



	
	
	
	
	
	

}
