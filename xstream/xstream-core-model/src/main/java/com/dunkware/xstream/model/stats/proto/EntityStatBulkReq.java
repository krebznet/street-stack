package com.dunkware.xstream.model.stats.proto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EntityStatBulkReq {
	
	public static class Builder {
		
		public static Builder builder(EntityStatReqType type) { 
			return new Builder(type);
		}
		
		private List<String> entities = new ArrayList<String>();
		private EntityStatBulkReq req  = new EntityStatBulkReq();
	
		private Builder(EntityStatReqType reqType) { 
			this.req.setType(reqType);
			this.req.setDate(LocalDate.now().minusDays(4));
			
		
			
			
		}
		
		public Builder setDate(LocalDate date) { 
			this.req.setDate(date);;
			return this;
		}
		
		public Builder setRelativeDays(int days) { 
			this.req.setRelativeDays(days);
			return this;
		}
		
		
		
		public Builder stream(String ident) { 
			this.req.setStream(ident);
			return this;
		}
		
		public Builder target(String target) { 
			req.setTarget(target);
			return this;
		}
		
		public Builder addEntity(String ident) { 
			entities.add(ident);
			return this;
		}
		
		public EntityStatBulkReq build() {
			req.setEntities(entities);
			return req;
		}
	}

	private List<String> entities; 
	private LocalDate date; 
	private int relativeDays;
	private String target;
	private String agg;
	private String stream;
	private EntityStatReqType type;
	
	public List<String> getEntities() {
		return entities;
	}
	public void setEntities(List<String> entities) {
		this.entities = entities;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getRelativeDays() {
		return relativeDays;
	}
	public void setRelativeDays(int relativeDays) {
		this.relativeDays = relativeDays;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getAgg() {
		return agg;
	}
	public void setAgg(String agg) {
		this.agg = agg;
	}
	public EntityStatReqType getType() {
		return type;
	}
	public void setType(EntityStatReqType type) {
		this.type = type;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	
	
	
	
	
	
}
