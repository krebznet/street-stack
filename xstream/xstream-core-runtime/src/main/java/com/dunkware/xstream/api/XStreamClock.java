package com.dunkware.xstream.api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface XStreamClock {
	
	public LocalTime getTime();
	
	public void setTime(LocalTime time);
	
	public void scheduleRunnable(Runnable runnable, int interval);
	
	public void unscheduleRunnable(Runnable runnable);
	
	public long getTimestamp();
	
	public LocalDateTime getLocalDateTime();
	
	public LocalTime getLocalTime();
	
	public long getSystemTimestamp();
	
	public LocalDate getDate();
	
	public void addListener(XStreamClockListener listener);
	
	public void removeListener(XStreamClockListener listneer);
	
	public LocalTime getStartTime();

}
