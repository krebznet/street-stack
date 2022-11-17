package com.dunkware.xstream.core.value;

import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.api.XStreamRowValue;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.api.XStreamVar;
import com.dunkware.xstream.model.value.SessionEntityValue;

public class VarCurrentValue implements XStreamRowValue {
	
	private XStreamVar var;

	@Override
	public void init(SessionEntityValue model, XStreamRow row) throws XStreamRuntimeException {
		XStreamVar var = row.getVar(model.getField().getIdentifier());
		if(var.getSize() == 0) { 
			
		}
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isResoolvable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object resolve() throws XStreamRuntimeException {
		return var.getValue(0);
		
	}

}
