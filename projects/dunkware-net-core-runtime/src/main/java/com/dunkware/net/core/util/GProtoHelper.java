package com.dunkware.net.core.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.helpers.DProtoHelper;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.common.util.time.range.CalendarDateTimeRange;
import com.dunkware.common.util.time.range.CalendarRange;
import com.dunkware.net.proto.core.GCalendarRange;
import com.dunkware.net.proto.core.GCalendarRangeType;
import com.dunkware.net.proto.core.GDate;
import com.dunkware.net.proto.core.GDateTime;
import com.dunkware.net.proto.core.GDurationRange;
import com.dunkware.net.proto.core.GTime;
import com.dunkware.net.proto.core.GTimeUnit;
import com.dunkware.net.proto.core.GTimeZone;
import com.google.protobuf.Timestamp;

public class GProtoHelper {
	
	private static Logger logger = LoggerFactory.getLogger(GProtoHelper.class);
	
	public static GTime toGTime(LocalTime time) { 
		return GTime.newBuilder().setHour(time.getHour()).setMinute(time.getMinute()).setSecond(time.getSecond()).build();
	}
	
	public static GTime toGTime(DTime time) { 
		return GTime.newBuilder().setHour(time.getHour()).setMinute(time.getMinute()).setSecond(time.getSecond()).build();
	}
	
	public static LocalTime toLocalTime(GTime time) { 
		return LocalTime.of(time.getHour(), time.getMinute(),time.getSecond());	
	}
	
	public static LocalTime toLocalTime(Timestamp ts, DTimeZone zone) { 
		return Instant
		.ofEpochSecond( ts.getSeconds() , ts.getNanos() ) 
		.atZone( DTimeZone.toZoneId(zone) ) 
		.toLocalTime();
	}
	
	public static String toTimeStringTimeStamp(Timestamp ts, DTimeZone zone) { 
		LocalTime time = toLocalTime(ts,zone);
		return DunkTime.toStringTimeStamp(time);
	}

	public static Timestamp toTimeStamp(LocalDateTime dt, DTimeZone timezone) {
		Instant instant = dt.toInstant(DProtoHelper.timeZoneToOffset(timezone));

		Timestamp result = Timestamp.newBuilder().setSeconds(instant.getEpochSecond()).setNanos(instant.getNano())
				.build();
		return result;
	}

	public static ZoneOffset zoneIdToOffset(ZoneId zoneId) {
		Instant instant = Instant.now(); // can be LocalDateTime
		ZoneOffset currentOffsetForMyZone = zoneId.getRules().getOffset(instant);
		return currentOffsetForMyZone;
	}

	public static ZoneOffset timeZoneToOffset(DTimeZone timezone) {
		Instant instant = Instant.now(); // can be LocalDateTime
		ZoneOffset currentOffsetForMyZone = DTimeZone.toZoneId(timezone).getRules().getOffset(instant);
		return currentOffsetForMyZone;
	}

	public static LocalDateTime toLocalDateTime(Timestamp ts, DTimeZone timezone) {
		return Instant.ofEpochSecond(ts.getSeconds(), ts.getNanos()).atZone(DTimeZone.toZoneId(timezone))
				.toLocalDateTime();

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
	
	public static int getDurationRangeSeconds(GDurationRange range) throws Exception { 
		if(range.getTimeUnit() == GTimeUnit.SECOND) { 
			return range.getValue();
		}
		if(range.getTimeUnit() == GTimeUnit.MINUTE) { 
			return range.getValue() * 60;
		}
		if(range.getTimeUnit() == GTimeUnit.HOUR) { 
			return range.getValue() * 3600;
		}
		
		throw new Exception("Time Unit " + range.getTimeUnit().name() + " not handled in get duration range seconds");
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
			LocalDateTime start = GProtoHelper.toLocalDateTime(range.getDateRange().getStartDate());
			LocalDateTime stop = GProtoHelper.toLocalDateTime(range.getDateRange().getStopDate());
			return CalendarDateTimeRange.newInstance(start, stop);
			
		}
		if(range.getType() == GCalendarRangeType.DATE_TIME_RANGE) { 
			LocalDateTime start = GProtoHelper.toLocalDateTime(range.getDateTimeRange().getStart());
			LocalDateTime stop = GProtoHelper.toLocalDateTime(range.getDateTimeRange().getStop());
			return CalendarDateTimeRange.newInstance(start, stop);
		
		}
		if(range.getType() == GCalendarRangeType.TIME_RANGE) { 
			LocalDate currentDate = LocalDate.now();
			LocalDateTime start = GProtoHelper.toLocalDateTime(range.getTimeRange().getStartTime(), currentDate);
			LocalDateTime stop = GProtoHelper.toLocalDateTime(range.getTimeRange().getStopTime(),currentDate);
			return CalendarDateTimeRange.newInstance(start, stop);
		}
		if(range.getType() == GCalendarRangeType.TIME_DURATION) { 
			LocalDateTime stop = LocalDateTime.now(DTimeZone.toZoneId(DTimeZone.NewYork));
			LocalDateTime start = GProtoHelper.toLocalDateTime(range.getDurationRange(), stop);
			return CalendarDateTimeRange.newInstance(start, stop);
		}
		throw new Exception("Range type " + range.getType().name() + " not handled in proto helper");
	}
	
	
	public static GTimeZone toGTimeZone(DTimeZone target) {
		if(target == DTimeZone.California) { 
			return GTimeZone.US_PACIFIC;
		}
		if(target == DTimeZone.Chicago) { 
			return GTimeZone.US_CENTRAL;
		}
		if(target == DTimeZone.NewYork) { 
			return GTimeZone.US_EASTERN;
		}
		if(target == DTimeZone.Denver) { 
			return GTimeZone.US_MOUNTAIN;
		}
		logger.error("DTImeZone " + target.name() + " not converted to GTimeZone");
		return GTimeZone.US_EASTERN;
	}
	

}
