package com.dunkware.common.util.dtime;

import java.time.ZoneId;
import java.util.TimeZone;

public enum DTimeZone {
	NewYork,Denver,Chicago,California;

	
	public static ZoneId toZoneId(DTimeZone zone) { 
		if(zone == NewYork) { 
			return TimeZone.getTimeZone("America/New_York").toZoneId();
		}
		if(zone == Denver) { 
			return TimeZone.getTimeZone("America/Denver").toZoneId();
		}
		if(zone == Chicago) { 
			return TimeZone.getTimeZone("America/Chicago").toZoneId();
		}
		if(zone == Chicago) { 
			return TimeZone.getTimeZone("America/Los_Angeles").toZoneId();
		}
		throw new DTimeException("TimeZone " +  zone.name() + " not converting to ZoneId");
	}
	
	public static DTimeZone fromZoneId(ZoneId id) { 
		if(id.getId().equals("America/New_York")) { 
			return DTimeZone.NewYork;
		}
		if(id.getId().equals("America/Denver")) { 
			return DTimeZone.Denver;
		}
		if(id.getId().equals("America/Chicago")) { 
			return DTimeZone.Chicago;
		}
		if(id.getId().equals("America/Los_Angeles")) { 
			return DTimeZone.California;
		}
		throw new DTimeException("Cannot convert from ZoneID " + id.getId());
		
	}

}
