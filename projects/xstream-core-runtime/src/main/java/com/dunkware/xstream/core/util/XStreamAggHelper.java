package com.dunkware.xstream.core.util;

import com.dunkware.xstream.xScript.RelativeSessionTimeRange;
import com.dunkware.xstream.xScript.SessionTimeUnit;

public class XStreamAggHelper {

	
	public static int sessionRelativeRangeSeconds(RelativeSessionTimeRange model) throws Exception {
		if(model.getTimeUnit() == SessionTimeUnit.MIN) {
			return model.getRelativeVale() * 60;
		}
		if(model.getTimeUnit() == SessionTimeUnit.SEC) { 
			return model.getRelativeVale();
		}
		throw new Exception("Time Range " + model.getTimeUnit().name() + " not handled");
	}
}
