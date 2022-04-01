package com.dunkware.common.util.dtime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.dunkware.common.util.dtime.json.DDateDeserializer;
import com.dunkware.common.util.dtime.json.DDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = DDateSerializer.class)
@JsonDeserialize(using = DDateDeserializer.class)
public class DDate {
	
	public static DDate parse(String text) {
		LocalDate date = LocalDate.parse(text);
		return new DDate(date);
	}

	public static DDate from(LocalDate date) {
		return new DDate(date);
	}
	
	public static String echo(String input) { 
		return input;
	}

	public static DDate now() {
		return new DDate(LocalDate.now());
	}

	private LocalDate date;

	public DDate() { 
		
	}
	private DDate(LocalDate date) {
		this.date = date;
	}

	public LocalDate get() {
		return date;
	}

	public boolean isAfter(DDate compare) {
		return date.isAfter(compare.get());
	}

	public boolean isBefore(DDate compare) {
		return date.isBefore(compare.get());
	}
	
	public String toMMDDYY() {
		return date.format(DateTimeFormatter.ofPattern("MMddyy"));
	}
	

}
