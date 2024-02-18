package com.dunkware.trade.net.data.server.stream.entitystats.cache;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CacheHelper {

	public static final LocalDate start = LocalDate.of(1978, 4, 29);
	
	public static long dateKey(LocalDate date) { 
		return  ChronoUnit.DAYS.between(start, date);
		
	}
	
	public static LocalDate date(long key) { 
		return start.plusDays(key);
	}
}
