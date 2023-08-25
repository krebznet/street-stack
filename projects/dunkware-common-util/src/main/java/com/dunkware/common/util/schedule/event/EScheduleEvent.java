package com.dunkware.common.util.schedule.event;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.common.util.schedule.model.Schedule;

public class EScheduleEvent extends DEvent {

	private Schedule schedule;
	
	public EScheduleEvent(Schedule schedule) { 
		this.schedule = schedule;
	}
}
