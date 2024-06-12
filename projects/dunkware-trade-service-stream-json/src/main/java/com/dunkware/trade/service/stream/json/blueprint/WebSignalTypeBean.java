package com.dunkware.trade.service.stream.json.blueprint;

import com.dunkware.utils.core.json.DunkJson;

public class WebSignalTypeBean {
	
	private String name;
	private String description;
	private String created; 
	private String status; 
	private String count;
	private String group;
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
	
	public static void main(String[] args) {
		WebSignalTypeBean b = new WebSignalTypeBean();
		b.setStatus("Active");
		b.setCount("232,232");
		b.setCreated("5/2/23");
		b.setDescription("Multiday Opening Breakout");
		b.setGroup("Breakout");
		b.setName("Breakout1");
		try {
			System.out.println(DunkJson.serializePretty(b));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	

}
