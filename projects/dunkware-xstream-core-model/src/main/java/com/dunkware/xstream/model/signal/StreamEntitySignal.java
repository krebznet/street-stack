package com.dunkware.xstream.model.signal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import com.dunkware.common.util.json.DJson;

public class StreamEntitySignal {
	
	private int stat;
	private int entity;
	private int type;
	LocalDateTime dateTime;
	
	
	public static void main(String[] args) {
		StreamEntitySignal sig = new StreamEntitySignal();
		sig.setStat(1);;
		sig.setEntity(1);
		sig.setDateTime(LocalDateTime.now());
		
		sig.setType(1);
		Map<Integer,Object> vars = new HashMap<Integer,Object>();
		vars.put(1, 23.5);
		vars.put(2, 32);
		vars.put(3, 323239.23);
		vars.put(4, 50003434);
		sig.setVars(vars);
		try {
			System.out.println(DJson.serializePretty(sig));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private Map<Integer, Object> vars = new HashMap<Integer,Object>();

	
	public void setType(int type) {
		this.type = type;
	}
	// 
	

	


	public LocalDateTime getDateTime() {
		return dateTime;
	}






	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}






	public int getEntity() {
		return entity;
	}

	public void setEntity(int entity) {
		this.entity = entity;
	}

	
	
	

	public Map<Integer, Object> getVars() {
		return vars;
	}





	public void setVars(Map<Integer, Object> vars) {
		this.vars = vars;
	}





	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}

	public int getType() {
		return type;
	}
	
	
	


	
	
	
	
	
	
	
	

}
