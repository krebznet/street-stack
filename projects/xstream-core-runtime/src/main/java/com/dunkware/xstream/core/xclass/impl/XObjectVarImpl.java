/**
 * 
 */
package com.dunkware.xstream.core.xclass.impl;

import com.dunkware.xstream.api.XObjectVar;
import com.dunkware.xstream.api.XObjectVarNull;
import com.dunkware.xstream.xScript.XVarType;

/**
 * @author Duncan Krebs
 * @date Oct 24, 2015
 * @category M10-Comcast
 */
public class XObjectVarImpl implements XObjectVar  {
	
	private XVarType _type;
	private Object _value;
	
	public XObjectVarImpl(XVarType type, Object value) {
		_type = type;
		_value = value; 
	}

	@Override
	public XVarType getType() {
		return _type;
	}

	
	public void setValue(Object value) {
		_value = value; 
	}

	@Override
	public Object getValue() {
		return _value; 
	}

	@Override
	public String getName() {
		return _type.getName();
	}

	/* (non-Javadoc)
	 * @see com.kntrade.sdk.xclass.api.runtime.XObjectVar#isNull()
	 */
	@Override
	public boolean isNull() {
		if (_value instanceof XObjectVarNull) {
			return true;
		}
		return false;
	}
	
	
	
}
