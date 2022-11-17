package com.dunkware.xstream.core.value;

import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.api.XStreamRowValue;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.model.util.TimeRangeSession;
import com.dunkware.xstream.model.value.EntitySignalCountSession;
import com.dunkware.xstream.model.value.SessionEntityValue;

public class SignalCountSession implements XStreamRowValue {

	@Override
	public void init(SessionEntityValue model, XStreamRow row) throws XStreamRuntimeException {
		EntitySignalCountSession sessionCount = model.getSignalCountSession();
		TimeRangeSession range = sessionCount.getTimeRange();
		// okay we need to timestamp signals on xrow 
		
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
		// TODO Auto-generated method stub
		return null;
	}

}
