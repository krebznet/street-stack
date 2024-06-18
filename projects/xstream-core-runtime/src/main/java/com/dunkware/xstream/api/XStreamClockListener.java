package com.dunkware.xstream.api;

import java.time.LocalDateTime;

public interface XStreamClockListener {
	
	public void timeUpdate(XStreamClock clock, LocalDateTime time);

}
