package com.dunkware.common.util.value;

public class DInt extends DValue<Integer> {

	private Integer value;

	public DInt() { 
		
	}
	
	public DInt(Integer value) { 
		this.value = value;
	}
	
	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public void setValue(Integer value) {
		this.value = value;
	}

}

// DDataType // Double Long DDataType