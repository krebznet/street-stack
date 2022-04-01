package com.dunkware.common.util.properties;

public class DPropertyObject {

	private String namespace;
	private String name; 
	
	private DProperties properties;
	
	public DPropertyObject(String namespace, String name, DProperties properties) { 
		this.name = name;
		this.namespace = namespace;
		this.properties = properties;
	}
	
	public String getNamespace() { 
		return namespace; 
	}
	
	public String getName() { 
		return name;
	}
	
	public DProperties getProperties() { 
		return properties;
	}
	
	public String getString(String property) throws DPropertiesException { 
		return properties.getString(property);
	}
	
	public int getInt(String property) throws DPropertiesException { 
		return properties.getInt(property);
	}

	public Boolean getBoolean(String property) throws DPropertiesException { 
		return properties.getBoolean(property);
	}
}
