package com.dunkware.common.util.observable;

public class DObservableTopic {

	private String name;
	
	public DObservableTopic(String name) { 
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DObservableTopic) {
			DObservableTopic compareTopic = (DObservableTopic) obj;
			return compareTopic.getName().equals(name);
		}
		return super.equals(obj);
	}
	
	
	
	
} 
