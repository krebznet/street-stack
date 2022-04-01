package com.dunkware.net.core.helpers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.time.range.CalendarDateTimeRange;
import com.dunkware.common.util.time.range.CalendarRange;
import com.dunkware.net.proto.core.GCalendarRange;
import com.dunkware.net.proto.core.GCalendarRangeType;
import com.dunkware.net.proto.core.GDate;
import com.dunkware.net.proto.core.GDateTime;
import com.dunkware.net.proto.core.GDuration;
import com.dunkware.net.proto.core.GDurationRange;
import com.dunkware.net.proto.core.GTime;
import com.dunkware.net.proto.core.GTimeRange;
import com.dunkware.net.proto.core.GTimeUnit;

public class ProtoCalendarHelper {
	
	public static GTime toGTime(LocalTime time) { 
		return GTime.newBuilder().setHour(time.getHour()).setMinute(time.getMinute()).setSecond(time.getSecond()).build();
	}
	
	public static GTime toGTime(DTime time) { 
		return GTime.newBuilder().setHour(time.getHour()).setMinute(time.getMinute()).setSecond(time.getSecond()).build();
	}
	
	public static LocalTime toLocalTime(GTime time) { 
		return LocalTime.of(time.getHour(), time.getMinute(),time.getSecond());	
	}
	
	public static LocalDateTime toLocalDateTime(GDate date) { 
		return LocalDateTime.of(date.getYear(), date.getMonth(), date.getDay(), 0, 0, 0);
	}
	public static LocalDateTime toLocalDateTime(GDateTime datetime) { 
		return LocalDateTime.of(datetime.getDate().getYear(), datetime.getDate().getMonth(), datetime.getDate().getDay(), datetime.getTime().getHour(), datetime.getTime().getMinute(), datetime.getTime().getSecond());
		
	}
	public static LocalDateTime toLocalDateTime(GTime time, LocalDate date) {
		LocalDateTime datTime  = LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), time.getHour(), time.getMinute(),time.getSecond());
		return datTime;
	}
	
	public static LocalDateTime toLocalDateTime(GDurationRange durationRange, LocalDateTime now) throws Exception { 
		LocalDateTime ret = now;
		if(durationRange.getTimeUnit() == GTimeUnit.DAY) { 
			ret = ret.minusDays(durationRange.getValue());
			return ret;
		}
		if(durationRange.getTimeUnit() == GTimeUnit.HOUR) { 
			ret = ret.minusHours(durationRange.getValue());
			return ret;
		}
		if(durationRange.getTimeUnit() == GTimeUnit.SECOND) { 
			ret = ret.minusSeconds(durationRange.getValue());
			return ret;
		}
		throw new Exception("GDurationRange to LocalDateTime Time Unit Not handled " + durationRange.getTimeUnit().name());
	}
	public static GDate toGDate(LocalDate date) { 
		return GDate.newBuilder().setDay(date.getDayOfMonth()).setMonth(date.getMonthValue()).setYear(date.getYear()).build();
	}
	
	public LocalDate toLocalDate(GDate date) { 
		return null;
	}
	
	public static CalendarRange toCalendarRange(GCalendarRange grange) { 
		return null;
	}
	
	public static CalendarDateTimeRange toCalendarDateTimeRange(GCalendarRange range, LocalDateTime now) throws Exception { 
		if(range.getType() == GCalendarRangeType.DATE_RANGE) { 
			LocalDateTime start = ProtoCalendarHelper.toLocalDateTime(range.getDateRange().getStartDate());
			LocalDateTime stop = ProtoCalendarHelper.toLocalDateTime(range.getDateRange().getStopDate());
			return CalendarDateTimeRange.newInstance(start, stop);
			
		}
		if(range.getType() == GCalendarRangeType.DATE_TIME_RANGE) { 
			LocalDateTime start = ProtoCalendarHelper.toLocalDateTime(range.getDateTimeRange().getStart());
			LocalDateTime stop = ProtoCalendarHelper.toLocalDateTime(range.getDateTimeRange().getStop());
			return CalendarDateTimeRange.newInstance(start, stop);
		
		}
		if(range.getType() == GCalendarRangeType.TIME_RANGE) { 
			LocalDate currentDate = LocalDate.now();
			LocalDateTime start = ProtoCalendarHelper.toLocalDateTime(range.getTimeRange().getStartTime(), currentDate);
			LocalDateTime stop = ProtoCalendarHelper.toLocalDateTime(range.getTimeRange().getStopTime(),currentDate);
			return CalendarDateTimeRange.newInstance(start, stop);
		}
		if(range.getType() == GCalendarRangeType.TIME_DURATION) { 
			LocalDateTime stop = LocalDateTime.now(DTimeZone.toZoneId(DTimeZone.NewYork));
			LocalDateTime start = ProtoCalendarHelper.toLocalDateTime(range.getDurationRange(), stop);
			return CalendarDateTimeRange.newInstance(start, stop);
		}
		throw new Exception("Range type " + range.getType().name() + " not handled in proto helper");
	}
	
	
	

}
