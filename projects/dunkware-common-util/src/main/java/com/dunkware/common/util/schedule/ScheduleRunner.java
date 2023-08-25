package com.dunkware.common.util.schedule;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.dtime.DZonedClock;
import com.dunkware.common.util.dtime.DZonedClockListener;
import com.dunkware.common.util.dtime.DZonedClockUpdater;
import com.dunkware.common.util.dtime.DZonedDateTime;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.schedule.model.Schedule;

public class ScheduleRunner implements DZonedClockListener {

	private TimerTask timerTask;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker;
	
	public static ScheduleRunner build(Schedule schedule, DEventNode eventNode) throws Exception { 
		return new ScheduleRunner(schedule, eventNode);
	}
	
	private List<DayOfWeek> days = new ArrayList<DayOfWeek>();
	private DZonedDateTime lastDateTime;
	private volatile boolean inSession = false;
	private volatile boolean isSessionDay = false;
	
	private DZonedClockUpdater clockUpdater;
	private DZonedClock clock;

	private DTime stopTime;
	private DTime startTime;
	
	private Schedule schedule;
	private DEventNode eventNode; 
	
	private ScheduleRunner(Schedule schedule, DEventNode node) { 
		this.schedule = schedule;
		this.eventNode = node;
		marker = MarkerFactory.getMarker(schedule.getName());
		for (Integer day : schedule.getDays()) {
			days.add(DayOfWeek.of(Integer.valueOf(day)));
		}
		stopTime = schedule.getStopTime();
		startTime = schedule.getStartTime();
		
		clockUpdater = DZonedClockUpdater.now(schedule.getTimeZone(), 1, TimeUnit.SECONDS);
		clock = clockUpdater.getClock();
		lastDateTime = clockUpdater.getClock().getDateTime();
	
		newDay();
		if (isSessionDay) {
			// do we start a half baked session ? 
			if (clock.isAfterLocalTime(startTime) && clock.isBeforeLocalTime(stopTime)) {
				
					if (logger.isDebugEnabled()) {
						logger.debug(marker, "Starting Schedule Runner with schedule in session");
					}

					//startSession();
					inSession = true;
			

			}
			
		}	
		//clock.addListener();
	}
	
	
	public DEventNode getEventNode() { 
		return eventNode;
	}
	
	public boolean inSession () { 
		return inSession;
	}
	
	public String getName() { 
		return schedule.getName();
	}
	
	public void dispose() { 
		
	}
	
	private void newDay() {
		isSessionDay = false;
		DayOfWeek today = clockUpdater.getClock().getDayOfWeek();
		
		if (days.contains(today)) {
			if (logger.isDebugEnabled()) {
			//'logger.debug(marker,"{} Scheduler New Day Set Session Day True", ent.getName());
			}
			isSessionDay = true;
		} else {
			if (logger.isDebugEnabled()) {
			//	logger.debug(marker,"{} Scheudler New Day Set Session Day False", ent.getName());

			}
		}
	}
	
	@Override
	public void clockUpdate(DZonedClock clock) {
		try {
			//latch.waitTillZero();
		} catch (Exception e) {
			// interrupted so what?
		}
		if (clock.getDateTime().toLocalDate().isAfter(lastDateTime.toLocalDate())) {
			newDay();
		}
		if (inSession) {
			if (clock.getDateTime().toLocalTime().get().isAfter(stopTime.get())) {
				try {
					inSession = false;
					logger.info(marker,"Schedule {} Stop", schedule.getName());

				} catch (Exception e) {
					logger.error(marker,"Stream Schedule Stopping {} Exception {}", getName(), e.toString(), e);
				}
			}
		} else { 
			if(!isSessionDay) {
				return;
			}
			
		}

		
	}

}
	
