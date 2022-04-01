package com.dunkware.common.util.datatype;

/**
 * Exception Class For DataType Related Functions
 * @author dkrebs
 *
 */
public class DataTypeException extends Exception  {
	
	private static final long serialVersionUID = -1230509925401628012L;

	public DataTypeException(String s) {
		super(s);
	}
	
	public DataTypeException(String s, Throwable t) { 
		super(s,t);
		
	}
}
