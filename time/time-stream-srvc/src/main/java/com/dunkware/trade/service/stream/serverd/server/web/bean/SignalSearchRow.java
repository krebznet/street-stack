package com.dunkware.trade.service.stream.serverd.server.web.bean;

import com.dunkware.utils.core.json.DunkJson;

public class SignalSearchRow {

	public static void main(String[] args) {
		SignalSearchResponse resp = new SignalSearchResponse();
		resp.setSearchTime(34.3);
		SignalSearchRow row = new SignalSearchRow();
		row.setId(1);
		row.setName("Breakout 1");
		row.setEntityId(3);
		row.setEntityName("Apple Corporation");
		row.setTimestamp("12:31:54 9:32:30");
		resp.getResults().add(row);
		try {
			System.out.println(DunkJson.serializePretty(resp));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	private long id; 
	private String name;
	private int entityId; 
	private String entityName;
	private String timestamp;
	
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
	public int getEntityId() {
		return entityId;
	}
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
	
	
	
	
