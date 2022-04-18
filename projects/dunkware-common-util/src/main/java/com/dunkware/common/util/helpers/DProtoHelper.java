package com.dunkware.common.util.helpers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import com.dunkware.common.util.dtime.DTimeZone;
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
	
	

}
