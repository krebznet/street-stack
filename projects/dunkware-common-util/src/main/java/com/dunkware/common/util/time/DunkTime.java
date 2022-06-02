package com.dunkware.common.util.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.dunkware.common.util.dtime.DTimeZone;
import com.google.protobuf.Timestamp;

public class DunkTime {

	public final static String YYYY_MM_DD = "yyyy-MM-dd";
	public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public final static String HH_MMM_SS = "HH:mm:ss";
	public static final String YYMMDD = "yyMMdd";
    public static final String ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final String YYYY_MM_DD_HH_mm_ss = "YYYY-MM-DD HH:mm:ss";
	public static void main(String[] args) {
		LocalDateTime dt = LocalDateTime.now(DTimeZone.toZoneId(DTimeZone.NewYork));
		System.out.println(format(dt, YYMMDD));
		
	// YYMMDD
		
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
		return DunkTime.format(dt, DunkTime.YYYY_MM_DD_HH_mm_ss);
	}
	
	public static LocalDateTime toLocalDateTime(Date dateToConvert) {
	    return new java.sql.Timestamp(
	      dateToConvert.getTime()).toLocalDateTime();
	}

	public static Date toDate(LocalDateTime dateToConvert) {
	    return java.sql.Timestamp.valueOf(dateToConvert);
	}
	
	public static LocalDateTime toLocalDateTime(Timestamp ts, DTimeZone timezone) {
		return Instant.ofEpochSecond(ts.getSeconds(), ts.getNanos()).atZone(DTimeZone.toZoneId(timezone))
				.toLocalDateTime();
	}
	
	


}
