package com.dunkware.trade.service.stream.server.repository;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name = "stream_resource_entity_scanner")
public class StreamScannerEntity {
	
	private String name; 
	@Column(columnDefinition = "text")
	private String model; 
	private LocalDateTime created; 
	private LocalDateTime updated; 
	private String streamIdentifier;
	private double streamVersion;
	

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	public String getStreamIdentifier() {
		return streamIdentifier;
	}

	public void setStreamIdentifier(String streamIdentifier) {
		this.streamIdentifier = streamIdentifier;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getStreamVersion() {
		return streamVersion;
	}

	public void setStreamVersion(double streamVersion) {
		this.streamVersion = streamVersion;
	}
	
	

	
	
	

}
