package com.dunkware.common.util.args;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Args {
	
	public static Args build(String[] args) throws Exception { 
		Map<String,String> properties = new HashMap<String,String>();
		Set<String> options = new HashSet<String>();
		
		for (String string : args) {
			if(string.startsWith("--")) { 
				if(string.contains("=")) { 
					String key = string.substring(2,string.indexOf("="));
					String value = string.substring(string.indexOf("=") + 1,string.length());
					properties.put(key, value);
				}	
			} else { 
				if(string.startsWith("-")) { 
					String option = string.substring(1,string.length());
					options.add(option);
				}	
			}
			
			
		}
		return new Args(properties,options);
	}
	
	
	private Map<String,String> properties;
	private Set<String> options;
	
	private Args(Map<String,String> properties, Set<String> options) { 
		 this.options = options;
		 this.properties = properties; 
	}
	
	public boolean hasOption(String option) { 
		return options.contains(option);
	}
	
	public String getProperty(String property) { 
		return properties.get(property);
	}
	
	public boolean hasProperty(String property) { 
		if(properties.get(property) == null) { 
			return false; 
		}
		return true;
	}
	

}
