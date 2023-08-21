package com.dunkware.common.util.data;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;

public class NetBean {
	
	private Map<String,Object> values = new HashMap<String,Object>();
	
	public void setValue(String key, Object value) { 
		this.values.put(key, value);
	}

	public Map<String, Object> getValues() {
		return values;
	}

	public void setValues(Map<String, Object> values) {
		this.values = values;
	}
	
	public Object getValue(String key) throws IllegalArgumentException { 
		if(values.containsKey(key)) { 
			return values.get(key);
		}
		
		throw new IllegalArgumentException("Net Bean key " + key + " not found");
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NetBean) {
			NetBean compareBean = (NetBean) obj;
			
			
		}
		return super.equals(obj);
	}
	
	
	
	

}
