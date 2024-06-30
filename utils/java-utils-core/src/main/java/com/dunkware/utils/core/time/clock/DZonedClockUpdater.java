package com.dunkware.utils.core.time.clock;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

public class DZonedClockUpdater {

	public static DZonedClockUpdater now(ZoneId timeZone, long updateInterval, TimeUnit updateUnit) { 
		DZonedClock clock = DZonedClock.now(timeZone);
		return new DZonedClockUpdater(clock, updateInterval, updateUnit);
	}
	
	private boolean disposed = false; 
	private DZonedClock clock;
	private long updateInterval;
	private TimeUnit timeUnit; 
	private UpdaterThread updater; 
	
	private DZonedClockUpdater(DZonedClock clock, long updateInterval, TimeUnit updateUnit) { 
		this.clock = clock;
		this.updateInterval = updateInterval;
		this.timeUnit = updateUnit;
		updater = new UpdaterThread();
		updater.start();
	}
	
	public DZonedClock getClock() {
		return clock;
	}
	
	public void dispose() { 
		if(!disposed) { 
			updater.interrupt();
		}
	}
	
	public boolean isDisposed() { 
		return disposed;
	}
	
	private class UpdaterThread extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				try {
					Thread.sleep(timeUnit.toMillis(updateInterval));
					clock.setDateTime(LocalDateTime.now(getClock().getZoneId()));
				} catch (Exception e) {
					disposed = true;
				}
			}
		}
	}
}
