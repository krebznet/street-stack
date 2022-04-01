package com.dunkware.trade.service.stream.server.controller.repository;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "stream_version")
public class StreamVersionDO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private double version;
	private LocalDateTime timestamp;
	@Column(columnDefinition = "text")
	private String bundle; 
	
	@ManyToOne()
	private StreamDO stream;

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

	public StreamDO getStream() {
		return stream;
	}

	public void setStream(StreamDO stream) {
		this.stream = stream;
	}
	
	
	


	
	
	
	
	
	

}
