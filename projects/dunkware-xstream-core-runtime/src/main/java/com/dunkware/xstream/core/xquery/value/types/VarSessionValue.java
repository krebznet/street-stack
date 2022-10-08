package com.dunkware.xstream.core.xquery.value.types;

import com.dunkware.xstream.api.XStreamException;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.core.xquery.value.XValue;
import com.dunkware.xstream.xScript.XValueType;

public class VarSessionValue implements XValue {

	private XValueType type;
	
	@Override
	public void validatePossible(XStreamRow row) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isResolvable(XStreamRow row) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object validateResolvable(XStreamRow row) throws XStreamException {
		// TODO Auto-generated method stub
		//row.getVar(null)
		//return null;
		return null;
	}



}
