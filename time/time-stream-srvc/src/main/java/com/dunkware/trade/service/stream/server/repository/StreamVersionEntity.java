package com.dunkware.trade.service.stream.server.repository;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "stream_version")
public class StreamVersionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private double version;
	private LocalDateTime timestamp;
	@Column(columnDefinition = "text")
	private String bundle; 
	
	@ManyToOne()
	private StreamEntity stream;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getVersion() {
		return version;
	}

	public void setVersion(double version) {
		this.version = version;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getBundle() {
		return bundle;
	}

	public void setBundle(String bundle) {
		this.bundle = bundle;
	}

	public StreamEntity getStream() {
		return stream;
	}

	public void setStream(StreamEntity stream) {
		this.stream = stream;
	}
	
	
	


	
	
	
	
	
	

}
