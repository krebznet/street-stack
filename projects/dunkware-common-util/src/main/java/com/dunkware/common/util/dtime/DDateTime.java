package com.dunkware.common.util.dtime;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.dunkware.common.util.dtime.json.DDateTimeDeserializer;
import com.dunkware.common.util.dtime.json.DDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = DDateTimeSerializer.class)
@JsonDeserialize(using = DDateTimeDeserializer.class)
public class DDateTime {

	
	public static DDateTime parse(String text) {
		LocalDateTime date = LocalDateTime.parse(text);
		return new DDateTime(date);
	}
	
	public static DDateTime of(DDate date, DTime time) {
		return new DDateTime(LocalDateTime.of(date.get(), time.get()));

	}

	public static DDateTime now() {
		return new DDateTime(LocalDateTime.now());
	}

	public static DDateTime now(DTimeZone timezone) {
		return new DDateTime(LocalDateTime.now(DTimeZone.toZoneId(timezone)));
	}
	
	public static DDateTime from(LocalDateTime dt) { 
		return new DDateTime(dt);
	}

	private volatile LocalDateTime dateTime;
	
	public DDateTime() { 
		
		
	}

	private DDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public LocalDateTime get() {
		return dateTime;
	}

	public int getHour() {
		return dateTime.getHour();
	}

	public int getMinute() {
		return dateTime.getMinute();
	}

	public int getSecond() {
		return dateTime.getSecond();
	}

	public int getNano() {
		return dateTime.getNano();
	}

	public int getYear() {
		return dateTime.getYear();
	}

	public int getMonthValue() {
		return dateTime.getMonthValue();
	}

	public int getDayOfMonth() {
		return dateTime.getDayOfMonth();
	}

	public int getDayOfYear() {
		return dateTime.getDayOfYear();
	}

	public String toSqlTimestamp() {
		return DDateTime.toSqlTimestamp(this);
	}

	public static String toSqlTimestamp(DDateTime dt) {
		StringBuilder out = new StringBuilder();
		out.append(dt.getYear());
		String monthString = String.valueOf(dt.getMonthValue());
		if (monthString.length() == 1) {
			monthString = "0" + monthString;
		}
		out.append("-");
		out.append(monthString);
		String day = String.valueOf(dt.getDayOfMonth());
		if (day.length() == 1) {
			day = "0" + day;
		}
		out.append("-");
		out.append(day);
		String hour = String.valueOf(dt.getHour());
		if (hour.length() == 1) {
			hour = "0" + hour;
		}
		out.append(" ");
		out.append(hour);
		out.append(":");
		String min = String.valueOf(dt.getMinute());
		if (min.length() == 1) {
			min = "0" + min;
		}
		out.append(min);
		out.append(":");
		String sec = String.valueOf(dt.getSecond());
		if (sec.length() == 1) {
			sec = "0" + sec;
		}
		out.append(sec);
		return out.toString();
	}

}
