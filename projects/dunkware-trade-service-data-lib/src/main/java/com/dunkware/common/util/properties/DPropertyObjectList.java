package com.dunkware.common.util.properties;

import java.util.List;

public class DPropertyObjectList {
	
	private List<DPropertyObject> objects;
	
	public DPropertyObjectList(List<DPropertyObject> objects) {
		this.objects = objects;
	}
	
	public List<DPropertyObject> getObjects() { 
		return objects;
	}

}
