package com.dunkware.utils.core.helpers;

public class DunkNumber {

	
	public static final int DOUBLE = 0; 
	public static final int INT = 1; 
	public static final int LONG = 2; 
	public static final int UNKNOWN = 3;
	
	
	public static void main(String[] args) {
		Number higher = 2323.23;
		Number lower = 23;
		System.out.println(compare(higher,lower));
				
	}

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
	
	public static boolean isFirstGreater(Number targetValue, Number compareValue) {
		   // ignoring null handling
	    double target = targetValue.doubleValue();
	    double compare = compareValue.doubleValue();
	    if(target == compare) { 
	    	return false;
	    }
	    if(target < compare) { 
	    	return false;
	    }
	    return true;
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
	
	public static Double toDouble(Object input) throws Exception {
		if (input instanceof Double) {
			Double dubValue = (Double) input;
			return dubValue;
		}
		if (input instanceof Integer) {
			Integer intValue = (Integer) input;
			return new Double(intValue);
		}
		if (input instanceof Long) {
			Long longValue = (Long) input;
			return new Double(longValue);
		}
		if (input instanceof String) {
			String stringValue = (String) input;
			try {
				return Double.valueOf(stringValue);
			} catch (Exception e) {
				throw e;
			}
		}
		try {
			return Double.valueOf(input.toString());
		} catch (Exception e) {
			throw e;
		}
	}

	public static Long toLong(Object input) throws Exception {

		if (input instanceof Integer) {
			Integer intValue = (Integer) input;
			return (long)intValue;
		}
		if (input instanceof Double) {
			Double dubValue = (Double) input;
			double dub = dubValue;
			return (long) dub;
		}
		if (input instanceof Long) {
			long longValue = (Long) input;
			return longValue;
		}
		if (input instanceof String) {
			String stringValue = (String) input;
			try {
				return Long.valueOf(stringValue);
			} catch (Exception e) {
				throw e;
			}
		}
		try {
			return Long.valueOf(input.toString());
		} catch (Exception e) {
			throw e;
		}
	}

	public static Integer toInteger(Object input) throws Exception {

		if (input instanceof Integer) {
			Integer intValue = (Integer) input;
			return intValue;
		}
		if (input instanceof Double) {
			Double dubValue = (Double) input;
			double dub = dubValue;
			return (int) dub;
		}
		if (input instanceof Long) {
			long longValue = (Long) input;
			return (int) longValue;
		}
		if (input instanceof String) {
			String stringValue = (String) input;
			try {
				return Integer.valueOf(stringValue);
			} catch (Exception e) {
				throw e;
			}
		}
		try {
			return Integer.valueOf(input.toString());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static Integer longToInt(Long value) { 
		long longValue = value; 
		return (int) longValue;
	}

}
