package com.dunkware.xstream.core;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.xstream.api.XStreamClock;
import com.dunkware.xstream.api.XStreamClockListener;

/**
 * We are going to assume 1 second updates on the clock
 * @author dkrebs
 *
 */
public class XStreamClockImpl implements XStreamClock {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker timeSetMarker = MarkerFactory.getMarker("XStreamClockSet");
	private Marker runnableMarker = MarkerFactory.getMarker("XStreamScheduledRunnable");
	
	private volatile DTime time; 
	private XStreamImpl stream; 
	private DDate date; 
	private DTimeZone timeZone; 
	private ZoneId zoneId; 
	
	private List<XStreamClockListener> listeners = new ArrayList<XStreamClockListener>();
	private Semaphore listenerLock = new Semaphore(1);
	
	private ConcurrentHashMap<Runnable, ScheduledRunnable> scheduledRunnables = new ConcurrentHashMap<Runnable, XStreamClockImpl.ScheduledRunnable>();
	
	
	public XStreamClockImpl(XStreamImpl stream) {
		this.stream = stream; 
		this.date = stream.getInput().getDate();
		this.timeZone = stream.getInput().getTimeZone();
		this.zoneId = DTimeZone.toZoneId(timeZone);
		
	}
	
	
	@Override
	public LocalDateTime getLocalDateTime() {
		return  LocalDateTime.of(date.get(), time.get());
		
	}



	
	@Override
	public LocalTime getLocalTime() {
		return time.get();
	}




	@Override
	public DTime getTime() {
		return time; 
	}

	@Override
	public void setTime(DTime time) {
		if(logger.isTraceEnabled()) { 
			logger.debug("Time Set {}",time.getHour() + ":" + time.getMinute() + ":" + time.getSecond());
		}
		this.time = time; 
		// in the same thread invoke listeners and schedulers
		// so we stay more in syn 
		LocalDateTime dt = getLocalDateTime();
		if(logger.isTraceEnabled()) {
			logger.trace(timeSetMarker,"Time Set {}",DunkTime.format(dt, DunkTime.YYYY_MM_DD_HH_MM_SS));
		}
		try {
			listenerLock.acquire();
			for (XStreamClockListener listener : listeners) {
				listener.timeUpdate(this, dt);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			
			listenerLock.release();
		}
		for (ScheduledRunnable scheduled : scheduledRunnables.values()) {
			scheduled.timeUpdate();
		}
		
		
	}
	
	

	@Override
	public void scheduleRunnable(Runnable runnable, int interval) {
		ScheduledRunnable scheduled = new ScheduledRunnable(runnable, interval);
		scheduledRunnables.put(runnable, scheduled);
	}

	@Override
	public void unscheduleRunnable(Runnable runnable) {
		int scheduledSize = scheduledRunnables.size();
		scheduledRunnables.remove(runnable);
		int afterSize = scheduledRunnables.size();
		
	}
	
	
	
	@Override
	public long getSystemTimestamp() {
		stream.getInput().getTimeZone();
		ZoneId zoneId = DTimeZone.toZoneId(stream.getInput().getTimeZone());
		ZonedDateTime systime = ZonedDateTime.now(zoneId);
		return systime.toInstant().toEpochMilli();
		
	}

	public static LocalDateTime convertToLocalDateTimeViaMilisecond(Date dateToConvert) {
	    return Instant.ofEpochMilli(dateToConvert.getTime())
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}
	
	
	public static LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
	    return Instant.ofEpochMilli(dateToConvert.getTime())
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	@Override
	public long getTimestamp() {
		LocalDateTime dt = LocalDateTime.of(date.get(), time.get());
		return DunkTime.toDate(dt).getTime();
		
		
	}
	@Override
	public DDate getDate() {
		return date;
	}

	private class ListenerRunnable implements Runnable {
		
		LocalDateTime time;
		
		public ListenerRunnable(LocalDateTime time) {
			this.time = time;
		}
		
		public void run() { 
			try {
				listenerLock.acquire();
				for (XStreamClockListener listener : listeners) {
					listener.timeUpdate(XStreamClockImpl.this, time);
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				listenerLock.release();
			}
		}
		
	};



	@Override
	public void addListener(XStreamClockListener listener) {
		try {
			listenerLock.acquire();
			listeners.add(listener);
		} catch (Exception e) {
			
		} finally { 
			listenerLock.release();
		}
		
	}




	@Override
	public void removeListener(XStreamClockListener listener) {
		try {
			listenerLock.acquire();
			listeners.remove(listener);
		} catch (Exception e) {
			
		} finally { 
			listenerLock.release();
		}
		
	}






	private class ScheduledRunnable { 
		
		private Runnable runnable; 
		private int interval; 
		private volatile AtomicInteger counter = new  AtomicInteger(0);
		
		public ScheduledRunnable(Runnable runnable, int interval) { 
			this.runnable = runnable; 
			this.interval = interval;
		}
		
		public synchronized void timeUpdate() { 
			
			if(counter.incrementAndGet() == interval) {
				if(logger.isTraceEnabled()) { 
					logger.trace(runnableMarker, "{}:{}",runnable.getClass().getName(),DunkTime.format(getLocalDateTime(), DunkTime.YYYY_MM_DD_HH_MM_SS));
				}
				stream.getExecutor().execute(runnable);
				counter.set(0);
			}
		}
		
	}
	

}
