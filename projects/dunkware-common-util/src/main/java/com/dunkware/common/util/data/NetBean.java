package com.dunkware.common.util.data;

import java.util.HashMap;
import java.util.Map;

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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NetBean) {
			NetBean compareBean = (NetBean) obj;
			
			
		}
		return super.equals(obj);
	}
	
	
	
	

}
