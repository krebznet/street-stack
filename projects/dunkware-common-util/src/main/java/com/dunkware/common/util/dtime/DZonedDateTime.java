package com.dunkware.common.util.dtime;

import java.time.ZonedDateTime;

import com.dunkware.common.util.dtime.json.DZonedDateTimeSerialized;
import com.dunkware.common.util.dtime.json.DZonedDatedTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = DZonedDateTimeSerialized.class)
@JsonDeserialize(using = DZonedDatedTimeDeserializer.class)
public class DZonedDateTime {
	
	public static DZonedDateTime parse(String serialized) { 
		return new DZonedDateTime(ZonedDateTime.parse(serialized));
	}
	public static DZonedDateTime now() { 
		return new DZonedDateTime(ZonedDateTime.now());
	}
	
	public static DZonedDateTime now(DTimeZone zone)	{ 
		return new DZonedDateTime(ZonedDateTime.now(DTimeZone.toZoneId(zone)));
	}
	
	public static DZonedDateTime of(ZonedDateTime dt) { 
		return new DZonedDateTime(dt);
	}
	
	private ZonedDateTime dateTime; 
	
	public DZonedDateTime(ZonedDateTime dateTime) { 
		this.dateTime = dateTime;
	}
	
	public ZonedDateTime dateTime() {
		return dateTime;
	}
	
	public boolean isAfter(DZonedDateTime dateTime) { 
		return dateTime.isAfter(dateTime);
	}
	
	public boolean isBefore(DZonedDateTime dateTime) { 
		return dateTime.isBefore(dateTime);
	}
	
	public boolean isEqual(DZonedDateTime dateTime) { 
		if(!isAfter(dateTime) && !isBefore(dateTime)) { 
			return true; 
		}
		return false; 
	}
	
	public DTimeZone getTimeZone() { 
		return DTimeZone.fromZoneId(dateTime.getZone());
	}
	
	public DTime toLocalTime() { 
		return DTime.from(dateTime.toLocalTime());
	}
	
	public DDate toLocalDate() { 
		return DDate.from(dateTime.toLocalDate());
	}

}
