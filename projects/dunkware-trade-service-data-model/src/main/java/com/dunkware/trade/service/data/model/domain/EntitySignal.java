package com.dunkware.trade.service.data.model.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import com.dunkware.common.util.json.DJson;

public class EntitySignal {
	
	private int stat;
	private int entity;
	private int type;
	LocalDate date; 
	LocalTime time; 
	
	public static void main(String[] args) {
		EntitySignal sig = new EntitySignal();
		sig.setStat(1);;
		sig.setEntity(1);
		sig.setTime(LocalTime.now());
		sig.setDate(LocalDate.now());
		sig.setType(1);
		Map<Integer,Number> vars = new HashMap<Integer,Number>();
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
	private Map<Integer, Number> vars = new HashMap<Integer,Number>();

	
	public void setType(int type) {
		this.type = type;
	}

	public int getEntity() {
		return entity;
	}

	public void setEntity(int entity) {
		this.entity = entity;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}
	
	
	public Map<Integer, Number> getVars() {
		return vars;
	}

	public void setVars(Map<Integer, Number> vars) {
		this.vars = vars;
	}

	public void setTime(LocalTime time) {
		this.time = time;
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
