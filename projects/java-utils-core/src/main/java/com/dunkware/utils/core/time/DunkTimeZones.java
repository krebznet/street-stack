package com.dunkware.utils.core.time;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class DunkTimeZones {

	
	public static final  String ZoneNewYork = "America/New_York";
	
	
	
	public static ZoneId zoneNewYork() { 
		return ZoneId.of(ZoneNewYork);
	}
	
	public static LocalTime timeNewYork() { 
		return LocalTime.now(zoneNewYork());
	}
	
	public static LocalDateTime dateTimeNewYork() { 
		return LocalDateTime.now(zoneNewYork());
	}
	
	public static ZoneId getZoneId(String input) throws Exception  { 
		try {
			return ZoneId.of(input);
		} catch (Exception e) {
			throw new Exception("Invalid TimeZone " + input + " " + e.toString());
		}
	}
	
	
}
