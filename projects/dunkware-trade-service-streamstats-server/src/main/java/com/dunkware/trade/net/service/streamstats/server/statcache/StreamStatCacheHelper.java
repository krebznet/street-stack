package com.dunkware.trade.net.service.streamstats.server.statcache;

public class StreamStatCacheHelper {
	
	public static final int DATA_TYPE_LONG = 1; 
	public static final int DATA_TYPE_DOUBLE = 2; 
	public static final int DATA_TYPE_INT = 3; 
	
	
	public static int getDataType(Number number)  { 
	
		if (number instanceof Long) {
			return DATA_TYPE_LONG;
		}
		if (number instanceof Double) {
			return DATA_TYPE_DOUBLE;
		}
		if (number instanceof Integer) {
			return DATA_TYPE_INT;
		}
		return DATA_TYPE_INT;
	}
	
	public static Number stringToNumber(String input, int dataType) { 
		if(dataType == DATA_TYPE_DOUBLE) { 
			return Double.valueOf(input);
		}
		if(dataType == DATA_TYPE_LONG) { 
			return Integer.valueOf(input);
		}
		// else assuming its long
		return Long.valueOf(input);
	}
	
	public static String numberToString(Number number) { 
		if (number instanceof Long) {
			Long longValue = (Long) number;
			return longValue.toString();
		}
		if (number instanceof Double) {
			Double longValue = (Double) number;
			return longValue.toString();
		}
		if (number instanceof Integer) {
			Integer longValue = (Integer) number;
			return longValue.toString();
		}
		return number.toString();
	}
	
	
	

}
