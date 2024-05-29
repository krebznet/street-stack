package com.dunkware.utils.core.time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DunkDateFormatter {

	private static DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyMMdd");
	private static DateTimeFormatter yyy_MM_dd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	

	public static String yyyyMMdd(LocalDate date) { 
		return yyyyMMdd.format(date);
	}
	
	public static String defaultFormat(LocalDate date) {
		return yyy_MM_dd.format(date);
		
		
	}
}
