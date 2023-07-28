package com.dunkware.common.util.helpers;

import java.math.BigDecimal;

public class DNumberHelper {
	
	public static final int DOUBLE = 0; 
	public static final int INT = 1; 
	public static final int LONG = 2; 
	public static final int UNKNOWN = 3;
	
	

	/**
	 * 0 if both values are equal 
	 * -1 if targetValue is smaller 
	 * 1 if targetValue is bigger 
	 * @param targetValue
	 * @param compareValue
	 * @return
	 */
	public static int compare(Number targetValue, Number compareValue) {
		   // ignoring null handling
	    double target = targetValue.doubleValue();
	    double compare = compareValue.doubleValue();
	    if(target == compare) { 
	    	return 0;
	    }
	    if(target < compare) { 
	    	return -1;
	    }
	    return 1;
	}
	
	public static int getNumberType(Number number) { 
		if (number instanceof Double) {
			return DOUBLE;
		}
		if(number instanceof Long) { 
			return LONG;
		}
		if(number instanceof Integer) {
			return INT;
		}
		return UNKNOWN;
	}

}
