package com.dunkware.trade.service.beach.server.resources.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "BeachSystemResourceDO")
@Table(name = "resource_system")
public class BeachResourceSystemDO {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	private String name; 
	@Column(columnDefinition = "text")
	private String json; 
	private int revision;
	
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
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public int getRevision() {
		return revision;
	}
	public void setRevision(int revision) {
		this.revision = revision;
	} 
	
	

}
