package com.dunkware.xstream.model.signal;

import java.time.LocalDateTime;
import java.util.Map;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTime;

public class StreamSignal {
	
	private int id; 
	private String ident;
	private String entIdent;
	private int entId; 
	private LocalDateTime time;
	private Map<String,Object> vars;
	private String streamIdent;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	public String getEntIdent() {
		return entIdent;
	}
	public void setEntIdent(String entIdent) {
		this.entIdent = entIdent;
	}
	public int getEntId() {
		return entId;
	}
	public void setEntId(int entId) {
		this.entId = entId;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public Map<String, Object> getVars() {
		return vars;
	}
	public void setVars(Map<String, Object> vars) {
		this.vars = vars;
	}
	public String getStreamIdent() {
		return streamIdent;
	}
	public void setStreamIdent(String streamIdent) {
		this.streamIdent = streamIdent;
	} 
	
	
	
	
	
	
	

}
