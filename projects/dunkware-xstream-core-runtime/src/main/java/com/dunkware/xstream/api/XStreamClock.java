package com.dunkware.xstream.api;

import java.time.LocalDateTime;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTime;

public interface XStreamClock {
	
	public DTime getTime();
	
	public void setTime(DTime time);
	
	public void scheduleRunnable(Runnable runnable, int interval);
	
	public void unscheduleRunnable(Runnable runnable);
	
	public long getTimestamp();
	
	public LocalDateTime getLocalDateTime();
	
	public long getSystemTimestamp();
	
	public DDate getDate();
	
	public void addListener(XStreamClockListener listener);
	
	public void removeListener(XStreamClockListener listneer);

}
