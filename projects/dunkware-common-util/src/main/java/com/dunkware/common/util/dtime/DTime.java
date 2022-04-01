package com.dunkware.common.util.dtime;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.dunkware.common.util.dtime.json.DTimeDeserializer;
import com.dunkware.common.util.dtime.json.DTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = DTimeSerializer.class)
@JsonDeserialize(using = DTimeDeserializer.class)
public class DTime {
	
	public static DTime now(DTimeZone zone) { 
		return new DTime(LocalTime.now(DTimeZone.toZoneId(zone)));
	}
	public static DTime of(int hour, int minute, int second, int nanoOfSecond) { 
		return new DTime(LocalTime.of(hour, minute,second,nanoOfSecond));
		
	}
	
	public static DTime parse(String formatted) { 
		return from(LocalTime.parse(formatted));
	}
	
	public static DTime of(int hour, int minute, int second) { 
		
		return new DTime(LocalTime.of(hour, minute,second));
	}
	
	public static DTime from(LocalTime time) { 
		return new DTime(time);
	}
	
	public static DTime now() { 
		return new DTime(LocalTime.now(DTimeZone.toZoneId(DTimeZone.NewYork)));
	}
	
	private LocalTime time; 
	
	public DTime() { 
		
	}
	
	public LocalTime get() { 
		return time;
	}
	
	public DTime(LocalTime time) { 
		this.time = time;
	}
	
	public boolean isAfter(DTime time) { 
		return this.time.isAfter(time.get());
	}
	
	public boolean isBefore(DTime time) { 
		return this.time.isBefore(time.get());
	}
	
	public int getHour() { 
		return time.getHour();
	}
	
	public int getMinute() { 
		return time.getMinute();
	}
	
	public int getSecond() { 
		return time.getSecond();
	}

	public long getNano() { 
		return time.getNano();
	}
	public String toMySqlTimestamp() { 
		return DTime.toMySqlTimestamp(this);
	}
	
	public String toHHmmSS() { 
		return DateTimeFormatter.ofPattern("HH:mm:ss").format(this.time);
	}
	
	public static String toMySqlTimestamp(DTime time) {
		StringBuilder out = new StringBuilder();
		if (time.getHour() < 10) {
			out.append("0" + time.getHour() + ":");
		} else {
			out.append(time.getHour() + ":");
		}
		if (time.getMinute() < 10) {
			out.append("0" + time.getMinute() + ":");
		} else {
			out.append(time.getMinute() + ":");
		}
		if (time.getSecond() < 10) {
			out.append("0" + time.getSecond());
		} else {
			out.append(time.getSecond());
		}
		return out.toString();
	}

	

}
