package com.dunkware.common.util.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import com.dunkware.common.util.dtime.DTimeZone;
import com.google.protobuf.Timestamp;

public class DunkTime {

	public final static String YYYY_MM_DD = "yyyy-MM-dd";
	public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public final static String HH_MMM_SS = "HH:mm:ss";
	public static final String YYMMDD = "yyMMdd";
    public static final String ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final String YYMMDDHHMMSS =  "YYMMDDHHmmss";
    public static void main2(String[] args) {
		
		// 1970-01-01T00:00:00Z
		// I think you have it right - we need to set the java.util.Date object 
		
		
		// #1. we need to bet setting/getting java.util.Date objects 
		// on the mongo documents. 
		
		// #2. Does date capture any timezone? // 1970-01-01T00:00:00Z
		LocalDate easternLocalDate = LocalDate.now(DTimeZone.toZoneId(DTimeZone.NewYork));
		LocalDate myTimeZoneLocalDate = LocalDate.now();
		Instant instant = Instant.ofEpochMilli(myTimeZoneLocalDate.toEpochDay());
		Date myDate = Date.from(instant);
		
		LocalDateTime ldt = LocalDateTime.now();
		ZoneOffset offset = ZoneOffset.ofHours( -5 ); 
		OffsetDateTime odt = ldt.atOffset( offset );
		myDate = Date.from(odt.toInstant());
		
		// now when we save this in Mongo, does it capture the offset? 
		
		//System.out.println(myDate.toString());
		
		
		
	}
    
    
    public static void main(String[] args) {
    	LocalDateTime d = LocalDateTime.now();
    	System.out.println(DunkTime.format(d, YYMMDDHHMMSS));
    	if(1 == 1) { return; }
    	ZonedDateTime zonedDateTimeSameEst = ZonedDateTime.of(LocalDateTime.parse("2022-01-01T01:00:00.000000000"), ZoneId.of("America/New_York"));
        ZonedDateTime zonedDateTimeSamePak = ZonedDateTime.of(LocalDateTime.parse("2022-01-01T01:00:00.000000000"), ZoneId.of("Asia/Karachi"));
        System.out.println("Dates coming into the system with timezone known");
        System.out.println(zonedDateTimeSameEst + "||" + zonedDateTimeSamePak);

        Date dateEstAdjusted = Date.from(zonedDateTimeSameEst.toInstant());
        Date datePakAdjusted = Date.from(zonedDateTimeSamePak.toInstant());
        System.out.println("Dates stored in Mongo as per UTC equal");
        System.out.println(dateEstAdjusted + "||" + datePakAdjusted);
        System.out.println("They actually look different, since they are same date time, but coming in from different time zones");

        System.out.println();
        ZonedDateTime reversedEst = dateEstAdjusted.toInstant().atZone(ZoneId.of("America/New_York"));
        ZonedDateTime reversedPak = dateEstAdjusted.toInstant().atZone(ZoneId.of("Asia/Karachi"));
        System.out.println("Reverse conversions and results");
        System.out.println(reversedEst + "||" + reversedPak);
        System.out.println("Awesome they are same as original");

        System.out.println();
        System.out.println("Lets have the est time changed to pak time to see what it looks like in the other timezone as an additional case");
        ZonedDateTime reversedEstAsPak = dateEstAdjusted.toInstant().atZone(ZoneId.of("Asia/Karachi"));
        System.out.println(reversedEstAsPak);
	}
    
    
    public static Date toDateWithOffset(LocalDateTime date, DTimeZone timeZone) { 
    	ZoneId zoneId = DTimeZone.toZoneId(timeZone);
    	Instant instant = Instant.from(date);
    	ZoneOffset zoneOffset = zoneId.getRules().getOffset(instant);
    	OffsetDateTime odt = OffsetDateTime.of(date, zoneOffset);
    	Date results = Date.from(odt.toInstant());
    	return results;
    	
    }
    
    public static String toStringDateStamp(LocalDate date) { 
    	return format(date, YYYY_MM_DD);
    }
	
	public static String toStringTimeStamp(LocalTime time) { 
		return format(time,HH_MMM_SS);
	}
	
	public static String toStringTimeStamp(LocalDateTime time) { 
		return format(time,HH_MMM_SS);
	}
	
	public static String formatHHMMSS(LocalDateTime dt) { 
		return format(dt,HH_MMM_SS);
	}

	public static String formatHHMMSS(LocalTime dt) { 
		return format(dt,HH_MMM_SS);
	}

	public static String format(OffsetDateTime dt, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return dt.format(formatter).toString();
	}
	
	public static String format(OffsetTime dt, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return dt.format(formatter).toString();
	}
	
	public static String format(LocalDateTime dt, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return dt.format(formatter).toString();
	}

	public static String format(LocalTime dt, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return dt.format(formatter).toString();
	}
	
	public static String format(LocalDate date, String format) { 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return date.format(formatter);
	}
	
	
	public static String formatDateTimeStamp(long time) { 
		LocalDateTime dt = toLocalDateTime(new Date(time));
		return DunkTime.format(dt, DunkTime.YYYY_MM_DD_HH_MM_SS);
	}
	
	public static LocalDateTime toLocalDateTime(Date dateToConvert) {
	    return new java.sql.Timestamp(
	      dateToConvert.getTime()).toLocalDateTime();
	}

	
	public static Date toDate(LocalDateTime dateToConvert) {
	    return java.sql.Timestamp.valueOf(dateToConvert);
	}
	
	public static LocalDate toLocalDate(Date dateToConvert) {
		 return dateToConvert.toInstant()
			      .atZone(DTimeZone.toZoneId(DTimeZone.NewYork))
			      .toLocalDate();
	}
	public static LocalDateTime toLocalDateTime(Timestamp ts, DTimeZone timezone) {
		return Instant.ofEpochSecond(ts.getSeconds(), ts.getNanos()).atZone(DTimeZone.toZoneId(timezone))
				.toLocalDateTime();
	}
	
	
	public static boolean inTimeDateRange(LocalDateTime fromDateTime, LocalDateTime toDateTime, LocalDateTime testDateTime) { 
		if(testDateTime.isAfter(fromDateTime) && toDateTime.isAfter(testDateTime)) { 
			return true; 
		}
		return false; 
		
	}
	
	public static ZoneOffset zoneOffset(ZoneId id) {
	    return ZoneOffset.ofTotalSeconds((int) 
	        TimeUnit.MILLISECONDS.toSeconds(
	            TimeZone.getTimeZone(id).getRawOffset()        // Returns offset in milliseconds 
			));
	}
	
	public static long daysBetween(LocalDate from, LocalDate to) { 
		 long result = ChronoUnit.DAYS.between(from, to);
		 return result;
	}
	
	public static long minutesBetween(LocalDateTime from, LocalDateTime to) { 
		return ChronoUnit.MINUTES.between(from, to);
	}
	
	public static long secondsBetween(LocalTime from, LocalTime to) { 
		long result = ChronoUnit.SECONDS.between(from, to);
		return result;
	}
	

	public static long toLocalTimeLong(LocalTime time) {

		return time.toSecondOfDay();

	}

	public static LocalTime toLocalTime(long value) {
		return LocalTime.ofSecondOfDay(value);
	}

}
