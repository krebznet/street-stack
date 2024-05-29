package com.dunkware.utils.core.time;

import java.time.ZoneId;

public class DunkTimeZones {

	
	public static final  String ZoneNewYork = "America/New_York";
	
	
	public static ZoneId zoneNewYork() { 
		return ZoneId.of(ZoneNewYork);
	}
}
