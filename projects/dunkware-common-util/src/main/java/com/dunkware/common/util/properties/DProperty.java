package com.dunkware.common.util.properties;

public class DProperty {
	
	private String name;
	private String value;
	
	public DProperty() { 
		
	}
	
	public DProperty(String name,String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DProperty) {
			DProperty compare = (DProperty) obj;
			if(compare.getName().equals(name)) { 
				if(compare.getValue().equals(value)) { 
					return true;
				}
			}
		}
		return false;
	}
	
	
	

}
