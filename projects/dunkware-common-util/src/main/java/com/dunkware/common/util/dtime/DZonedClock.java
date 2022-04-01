package com.dunkware.common.util.dtime;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Going to use a ZonedDateTime 
 * @author dkrebs
 *
 */
public class DZonedClock {
	
	public static DZonedClock now(DTimeZone timezone) { 
		return new DZonedClock(DZonedDateTime.now(timezone));
	}
	
	
	private DZonedDateTime dateTime; 
	private List<DZonedClockListener> listeners = new ArrayList<DZonedClockListener>();
	private Semaphore listenerLock = new Semaphore(1);
	
	private DZonedClock(DZonedDateTime datetime) { 
		this.dateTime = datetime; 
		
	}
	
	public void addListener(DZonedClockListener listener) { 
		try {
			listenerLock.acquire();
			listeners.add(listener);
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			listenerLock.release();
		}
	}
	
	public void removeListener(DZonedClockListener listener) { 
		try {
			listenerLock.acquire();
			listeners.remove(listener);
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			listenerLock.release();
		}
	}
	
	public DZonedDateTime getDateTime() { 
		return dateTime;
	}
	
	public void setDateTime(DZonedDateTime dateTime) { 
		this.dateTime = dateTime; 
		notifyUpdate();
	}
	
	
	public DayOfWeek getDayOfWeek() { 
		return dateTime.dateTime().getDayOfWeek();
	}


	public boolean isBeforeLocalTime(DTime localtime) { 
		 return dateTime.toLocalTime().isBefore(localtime);
	}
	

	public boolean isAfterLocalTime(DTime localtime) { 
		 return dateTime.toLocalTime().isAfter(localtime);
	}
	
	
	private void notifyUpdate() { 
		try {
			listenerLock.acquire();
			for (DZonedClockListener listener : listeners) {
				listener.clockUpdate(this);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			listenerLock.release();
		}
	}

}
