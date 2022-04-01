package com.dunkware.common.util.helpers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.dtime.DTimeZone;

public class DDateTimeHelper {

	public static Date convertLocalDateTimeToDate(LocalDateTime dateToConvert, ZoneId zone) {
		return java.util.Date.from(dateToConvert.atZone(zone).toInstant());
	}
	
	public static Date convertLocalTimeToDate(LocalTime time, ZoneId zone) {
		LocalDateTime dateToConvert = LocalDateTime.of(LocalDate.now(), time);
		return java.util.Date.from(dateToConvert.atZone(zone).toInstant());
	}
	
	public static LocalDateTime localDateTimeFromLong(long time, ZoneId zone) { 
        LocalDateTime utcDateTimeForCurrentDateTime = Instant.ofEpochMilli(time).atZone(DTimeZone.toZoneId(DTimeZone.NewYork)).toLocalDateTime();
        return utcDateTimeForCurrentDateTime;
	}
	
	public static LocalDateTime dateToLocalDateTime(Date dt, ZoneId zone) { 
		 return dt.toInstant()
			      .atZone(DTimeZone.toZoneId(DTimeZone.NewYork)).toLocalDateTime();
			      
	}

	// NOTICE TIMEZONE ATTACHED .000Z We need to parse with ZonedDateTime
	public static void main(String[] args) {
		String test = "2021-05-19T09:32:29.000Z";
		try {
			ZonedDateTime dt = ZonedDateTime.parse(test);
			System.out.println(dt.getMinute());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}
	
	public static void main2(String[] args) {
		DDate date = DDate.now();
		DTime time = DTime.now();
		Calendar cal = Calendar.getInstance();
		LocalDateTime dt = LocalDateTime.of(date.get(), time.get());
		ZoneId  in = DTimeZone.toZoneId(DTimeZone.NewYork);
		Date dateToConvert = java.util.Date
			      .from(dt.atZone(DTimeZone.toZoneId(DTimeZone.NewYork)).toInstant());
		System.out.println(dateToConvert.toInstant().toEpochMilli());
		long opmilli = dateToConvert.toInstant().toEpochMilli();
		
		String timeColonPattern = "dd/MM/yyyy HH:mm:ss";
		DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern(timeColonPattern);
		
		Date myDate = new Date(opmilli);
		LocalDateTime results = DDateTimeHelper.dateToLocalDateTime(myDate, DTimeZone.toZoneId(DTimeZone.NewYork));
		System.out.println(results.format(timeColonFormatter));
		
	}
	
}
