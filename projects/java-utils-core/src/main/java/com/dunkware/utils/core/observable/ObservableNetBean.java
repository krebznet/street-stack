package com.dunkware.utils.core.observable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ObservableNetBean extends ObservableBean {
	
	private Map<String,Object> properties = new ConcurrentHashMap<String,Object>();

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}
	
	public void updateProperties(Map<String,Object> propeties) { 
		for (String key : properties.keySet()) {
			this.properties.put(key, properties.get(key));
		}
		notifyUpdate();
	}
	
	public void updateProperty(String key, Object value) { 
		this.properties.put(key, value);
		notifyUpdate();
	}
	
	

}