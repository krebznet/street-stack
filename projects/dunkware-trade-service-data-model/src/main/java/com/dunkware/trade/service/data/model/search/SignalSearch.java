package com.dunkware.trade.service.data.model.search;

import java.util.ArrayList;
import java.util.List;

public class SignalSearch {
	
	private List<Integer> signalTypes = new ArrayList<Integer>();
	private List<Integer> entities  = new ArrayList<Integer>();
	
	public List<Integer> getSignalTypes() {
		return signalTypes;
	}
	public void setSignalTypes(List<Integer> signalTypes) {
		this.signalTypes = signalTypes;
	}
	public List<Integer> getEntities() {
		return entities;
	}
	public void setEntities(List<Integer> entities) {
		this.entities = entities;
	}
	
	

}
