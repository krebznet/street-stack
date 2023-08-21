package com.dunkware.common.util.dtime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
	
	public static void main(String[] args) {
		
	}

	public static DDate from(Date dateToConvert) { 
		 LocalDate ld = Instant.ofEpochMilli(dateToConvert.getTime())
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
		 return from(ld);
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

	public boolean isSameDay(DDate compare) { 
		if(compare.get().isAfter(date) == false) { 
			if(compare.get().isBefore(date) == false) { 
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 223434  + get().getYear() + get().getMonthValue() + get().getDayOfMonth();
				
	}


	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DDate) {
			DDate compare = (DDate) obj;
			if(compare.get().getYear() == get().getYear()) { 
				if(compare.get().getMonthValue() == get().getMonthValue()) {
					if(compare.get().getDayOfMonth() == get().getDayOfMonth()) { 
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	

}
