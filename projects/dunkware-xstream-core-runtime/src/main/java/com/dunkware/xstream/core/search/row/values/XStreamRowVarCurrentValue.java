package com.dunkware.xstream.core.search.row.values;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.api.XStreamResolveException;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.core.search.row.XStreamRowValue;
import com.dunkware.xstream.model.query.XStreamRowValueModel;

public class XStreamRowVarCurrentValue implements XStreamRowValue {

	private XStreamRowValueModel model;
	private XStream stream;
	
	@Override
	public void init(XStreamRowValueModel model, XStream stream) throws XStreamQueryException {
		this.model = model;
		this.stream  = stream;
	}

	@Override
	public boolean canResolve(XStreamEntity row) throws XStreamQueryException {
		XStreamEntityVar var = row.getVar(model.getVarIdent());
		if(var == null) { 
			throw new XStreamQueryException("Variable " + model.getVarIdent() + " not found");
		}
		if(var.getSize() > 0) { 
			return true; 
		}
		return false;
	}

	@Override
	public Number resolve(XStreamEntity row) throws XStreamResolveException, XStreamQueryException {
		Object value = row.getVar(model.getVarIdent()).getValue(0);
		if (value instanceof Number) {
			Number retValue = (Number) value;
			return retValue;
		}
		throw new XStreamQueryException("Resolving variable value that is not instance of Number " + value.getClass().getName());
	}
	
	

}
