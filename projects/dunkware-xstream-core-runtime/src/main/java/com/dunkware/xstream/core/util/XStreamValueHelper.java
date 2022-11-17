package com.dunkware.xstream.core.util;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamRowValue;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.core.value.VarCurrentValue;
import com.dunkware.xstream.model.value.SessionEntityValue;
import com.dunkware.xstream.model.value.SessionEntityValueType;

public class XStreamValueHelper {
	
	
	public static XStreamRowValue createRowValue(XStream stream, SessionEntityValue value) throws XStreamRuntimeException {
		// get the row 
		
		if(value.getType() == SessionEntityValueType.CurrentValue) { 
			// value.getField(); -- need the row prior to  
			VarCurrentValue object = new VarCurrentValue();
			object.init(value, null);
			
		}
		
		if(value.getType() == SessionEntityValueType.AggSession) { 
			// agg session 
		}
		
		if(value.getType() == SessionEntityValueType.AggHistorical) {
			
		}
		
		if(value.getType() == SessionEntityValueType.SignalCountHistorical) {
			
		}
		
		if(value.getType() == SessionEntityValueType.SignalCountSession) {
			
		}
		
		return null; 
		
	}

}
