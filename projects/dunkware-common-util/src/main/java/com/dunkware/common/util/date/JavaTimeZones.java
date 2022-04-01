package com.dunkware.common.util.date;

import java.util.TimeZone;

public class JavaTimeZones {
	
	public static final String NewYork = "America/New_York";
	
	
	public static TimeZone getTimeZone(String constant) throws Exception {
		validateTimeZone(constant);;
		return TimeZone.getTimeZone(constant);
	}
	
	public static void validateTimeZone(String zone) throws Exception { 
		try {
			if(zone.equals(NewYork)) { 
				return;
			}
		} catch (Exception e) {
			throw new Exception("Invalid Time Zone " + zone);
		}
	}
}
