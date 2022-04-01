package com.dunkware.common.util.helpers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.net.proto.core.GTimeZone;
import com.dunkware.net.proto.core.GTimeZone;
import com.google.protobuf.Timestamp;

public class DProtoHelper {
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
	
	public static String toTimeZoneName(GTimeZone timeZone) { 
		return DTimeZone.toZoneName(toDTimeZone(timeZone));
	}
	
	public static DTimeZone toDTimeZone(GTimeZone timeZone) { 
		if(timeZone == GTimeZone.US_CENTRAL) { 
			return DTimeZone.Chicago;
		}
		if(timeZone == GTimeZone.US_EASTERN) { 
			return DTimeZone.NewYork;
		}
		if(timeZone == GTimeZone.US_MOUNTAIN) { 
			return DTimeZone.Denver;
		}
		if(timeZone == GTimeZone.US_PACIFIC) { 
			return DTimeZone.California;
		}
		return null;
	}
	
	public static ZoneId toZoneId(GTimeZone zone) { 
		return DTimeZone.toZoneId(toDTimeZone(zone));
	}

	
	
}
