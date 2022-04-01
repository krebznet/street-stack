package com.dunkware.common.util.helpers;

import java.util.Date;

public class DDateHelper {
	
	public static long getTime() {
		return new Date().getTime();
	}
	
	public static long getSecondsDiff(Date date1, Date date2) { 
		long diffInSeconds = Math.abs((date1.getTime() - date2.getTime()) / 1000);
		return diffInSeconds;
	}

}
