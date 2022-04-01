package com.dunkware.common.util.value;

public class DString extends DValue<String> {

	private String value;
	
	public DString() { 
		
	}
	
	public DString(String value) { 
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	
}
