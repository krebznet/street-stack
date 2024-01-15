package com.dunkware.common.util.time.range;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.time.DunkTime;

public class DunkDateTimeRange {

	
	public static DunkDateTimeRange absoluteDate(LocalDate date) { 
		LocalDateTime from = LocalDateTime.of(date, DunkTime.dayStartTime());
		LocalDateTime to = LocalDateTime.of(date, DunkTime.dayTimeEnd());
		return new DunkDateTimeRange(from,to);
	}
	
	public static DunkDateTimeRange relativeMinuteRange(int minutes) {
		LocalDateTime to = LocalDateTime.now(DTimeZone.toZoneId(DTimeZone.NewYork)).minusMinutes(4);
		LocalDateTime from = to.minusMinutes(minutes);
	
		return new DunkDateTimeRange(from,to);
	}
	
	
	public static DunkDateTimeRange relativeDayRange(int daysBack) {
		LocalDateTime to = LocalDateTime.of(LocalDate.now(DTimeZone.toZoneId(DTimeZone.NewYork)), DunkTime.dayStartTime());
		LocalDateTime from = to.minusDays(daysBack);
		return new DunkDateTimeRange(from,to);
	}
	
	
	private LocalDateTime from; 
	private LocalDateTime to; 
	
	private DunkDateTimeRange(LocalDateTime from, LocalDateTime to) { 
			this.from = from;
			this.to = to;
	}
	
	public boolean inRange(LocalDateTime dateTime) { 
		if(dateTime.isAfter(from) || dateTime.isEqual(from)) { 
			if(dateTime.isBefore(to) || dateTime.isEqual(to)) {
				return true;
			}
		}
		return false; 
	}
	
	
	public long milisecondsFrom() { 
		return DunkTime.toMilliseconds(from);
	}
	
	public long millsecondsTo() { 
		return DunkTime.toMilliseconds(to);
	}
	
	public long bucketDuration() { 
		return ChronoUnit.MILLIS.between(from, to);
	}
	
	
	
	
}
