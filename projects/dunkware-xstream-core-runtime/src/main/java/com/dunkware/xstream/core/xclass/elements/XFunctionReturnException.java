/**
 * 
 */
package com.dunkware.xstream.core.xclass.elements;

/**
 * @author Duncan Krebs
 * @date Oct 24, 2015
 * @category M10-Comcast
 */
public class XFunctionReturnException extends RuntimeException {
	
	private Object _value; 
	public XFunctionReturnException(Object value) { 
		_value = value; 
	}
	
	public Object getReurnValue() { 
		return _value; 
	}

}
