package com.dunkware.trade.service.stream.server.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "stream_blueprint_signal")
public class StreamBlueprintSignalEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@Column(columnDefinition = "text")
	private String model; 
	
	private LocalDateTime created;
	private LocalDate effective; 
	private LocalDateTime deleted; 
	
	@Enumerated(EnumType.ORDINAL)
	private StreamBlueprintSignalStatus status;
	
	private long streamId; 

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public LocalDate getEffective() {
		return effective;
	}

	public void setEffective(LocalDate effective) {
		this.effective = effective;
	}

	public LocalDateTime getDeleted() {
		return deleted;
	}

	public void setDeleted(LocalDateTime deleted) {
		this.deleted = deleted;
	}


	public long getStreamId() {
		return streamId;
	}

	public void setStreamId(long streamId) {
		this.streamId = streamId;
	}

	public StreamBlueprintSignalStatus getStatus() {
		return status;
	}

	public void setStatus(StreamBlueprintSignalStatus status) {
		this.status = status;
	} 
	
	
	
	
	
	
	
	

}
