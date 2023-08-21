package com.dunkware.trade.service.stream.server.blueprint;

import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.observable.ObservableBean;

public class StreamBlueprintSignalBean extends ObservableBean {
	
	private String name;
	private String description;
	private String created; 
	private String status; 
	private String count;
	private String group;
	private long id; 
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String active) {
		this.status = active;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public static void main(String[] args) {
		StreamBlueprintSignalBean b = new StreamBlueprintSignalBean();
		b.setStatus("Active");
		b.setCount("232,232");
		b.setId(3);
		b.setCreated("5/2/23");
		b.setDescription("Multiday Opening Breakout");
		b.setGroup("Breakout");
		b.setName("Breakout1");
		try {
			System.out.println(DJson.serializePretty(b));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	

}
