package com.dunkware.xstream.net.core.container.search2.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerSearchException;
import com.dunkware.xstream.net.model.search.Time;
import com.dunkware.xstream.net.model.search.TimeRangeSession;
import com.dunkware.xstream.net.model.search.TimeRangeSessionType;
import com.dunkware.xstream.net.model.search.TimeUnit;

public class StreamSearchUtil {
	
	/**
	 * Checks if there is enough data set for the session time range 
	 * @param entity
	 * @param range
	 * @return
	 */
	public static boolean canFillTimeRangeSession(ContainerEntity entity, TimeRangeSession range) throws ContainerSearchException {
		
		if(range.getType() == TimeRangeSessionType.Today) { 
			return true; 
		}
		LocalDateTime now = entity.getContainer().getTime();
		LocalDateTime sessionStart = entity.getContainer().getStartTime();
		if(range.getType() == TimeRangeSessionType.Relative) { 
			int seconds = getTimeRangeSeconds(range.getRelativeValue(), range.getRelativeUnit());
			if(now.minusSeconds(seconds).isAfter(sessionStart))	{ 
				return true; 
			} else { 
				return false; 
			}
			
		}
		if(range.getType() == TimeRangeSessionType.Absolute) { 
			LocalTime startTime = toLocalTime(range.getAbsoluteStart());
			LocalTime endTime = toLocalTime(range.getAbsoluteEnd());
			Time absoluteStart = range.getAbsoluteStart();
			Time absoluteStop = range.getAbsoluteEnd();
			LocalTime  absoluteStartLocalTime = LocalTime.of(absoluteStart.getHour(), absoluteStart.getMinute(), absoluteStart.getSecond());
			LocalTime  absoluteStopLocalTime = LocalTime.of(absoluteStop.getHour(), absoluteStop.getMinute(),absoluteStop.getSecond());
			
			if(startTime.isAfter(absoluteStartLocalTime) && endTime.isBefore(absoluteStopLocalTime)) {
				return true;
			} else { 
				return false;
			}
		}
		
		
		throw new ContainerSearchException("Unhandled code in canFillTimeRangeSession " + range.getType());
	}
	
	public static List<Object> getTimeRangeSessionVars(ContainerEntity entity, TimeRangeSession session, String var) throws ContainerSearchException { 
		// this could be optimized; 
		return null;  // isinstance ContainerValue implements comparable 
	}
	
	
	public static LocalTime toLocalTime(Time time) { 
		return LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());
		
	}
	
	public static int getTimeRangeSeconds(int value, TimeUnit unit) { 
		if(unit == TimeUnit.Seconds) { 
			return value; 
		}
		if(unit == TimeUnit.Minutes) { 
			return value * 60; 
		}
		if(unit == TimeUnit.Hours) { 
			return 3600 * value;
		}
		if(unit == TimeUnit.Days) { 
			return 86400 * value;
		}
		// log me maybe? 
		return value;
	}

}
