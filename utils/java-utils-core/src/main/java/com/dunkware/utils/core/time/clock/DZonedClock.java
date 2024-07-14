package com.dunkware.utils.core.time.clock;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Going to use a LocalDateTime 
 * @author dkrebs
 *
 */
public class DZonedClock {
	
	public static DZonedClock now(ZoneId zoneId) { 
		return new DZonedClock( LocalDateTime.now(zoneId), zoneId);
	}
	
	
	private LocalDateTime dateTime; 
	private List<DZonedClockListener> listeners = new ArrayList<DZonedClockListener>();
	private Semaphore listenerLock = new Semaphore(1);
	private ZoneId zoneId; 
	
	private DZonedClock(LocalDateTime datetime, ZoneId zoneId) { 
		this.dateTime = datetime; 
		this.zoneId = zoneId; 
	}
	
	public void addListener(DZonedClockListener listener) { 
		try {
			listenerLock.acquire();
			listeners.add(listener);
		} catch (Exception e) {
			
		} finally { 
			listenerLock.release();
		}
	}
	
	public void removeListener(DZonedClockListener listener) { 
		try {
			listenerLock.acquire();
			listeners.remove(listener);
		} catch (Exception e) {
			
		} finally { 
			listenerLock.release();
		}
	}
	
	public LocalDateTime getDateTime() { 
		return dateTime;
	}
	
	public void setDateTime(LocalDateTime dateTime) { 
		this.dateTime = dateTime; 
		notifyUpdate();
	}
	
	
	public DayOfWeek getDayOfWeek() { 
		return dateTime.getDayOfWeek();
	}

	
	public ZoneId getZoneId() { 
		return zoneId;
	}

	public boolean isBeforeLocalTime(LocalTime localtime) { 
		 return dateTime.toLocalTime().isBefore(localtime);
	}
	

	public boolean isAfterLocalTime(LocalTime localtime) { 
		 return dateTime.toLocalTime().isAfter(localtime);
	}
	
	
	private void notifyUpdate() { 
		try {
			listenerLock.acquire();
			for (DZonedClockListener listener : listeners) {
				listener.clockUpdate(this);
			}
		} catch (Exception e) {
			
		} finally { 
			listenerLock.release();
		}
	}

}
