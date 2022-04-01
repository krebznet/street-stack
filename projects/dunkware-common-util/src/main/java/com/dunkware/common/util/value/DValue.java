package com.dunkware.common.util.value;

import java.beans.Transient;

import com.dunkware.common.util.helpers.DConverter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class DValue<E> {

	public abstract E getValue();
	
	public abstract void setValue(E value);
	
	@Transient
	public String getString() {
		return getValue().toString();
	}
	
	@Transient
	public Integer getInt() { 
		try {
			return DConverter.toInteger(getValue());	
		} catch (Exception e) {
			throw new DValueException("toInt() Exception for value " + getValue().toString() + " " + e.toString());
		}
		
	}
	
}
