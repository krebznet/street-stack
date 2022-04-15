package com.dunkware.trade.service.data.service.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity(name = "DataStreamEntity")
@Table(name = "stream_repository")
public class DataStreamEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	private String name; 
	
	private LocalDateTime created;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "stream_id")
	List<DataStreamSessionEntity> sessions = new ArrayList<DataStreamSessionEntity>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<DataStreamSessionEntity> getSessions() {
		return sessions;
	}

	public void setSessions(List<DataStreamSessionEntity> sessions) {
		this.sessions = sessions;
	}
	
	


	
	
	
	
	
	
	
	

}
