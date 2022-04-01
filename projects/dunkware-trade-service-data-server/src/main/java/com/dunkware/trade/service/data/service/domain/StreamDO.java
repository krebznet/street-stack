package com.dunkware.trade.service.data.service.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("data_service_streams")
public class StreamDO {

	@Id
	private ObjectId _id;
	private String stream;
	private LocalDateTime created;
	private String timeZone; 
	
	@Field("entities")
	private List<StreamDOEntity> entities = new ArrayList<StreamDOEntity>();
	
	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String get_id() {
		return _id.toHexString();
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public List<StreamDOEntity> getEntities() {
		return entities;
	}

	public void setEntities(List<StreamDOEntity> entities) {
		this.entities = entities;
	}
	
	
	
	
	

}
