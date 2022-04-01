package com.dunkware.xstream.core.extensions;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.xstream.xproject.model.XStreamExtensionType;

public class TimeUpdaterExtType extends XStreamExtensionType {

	private DTimeZone timeZone;

	public DTimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(DTimeZone timeZone) {
		this.timeZone = timeZone;
	} 
	
	
}
