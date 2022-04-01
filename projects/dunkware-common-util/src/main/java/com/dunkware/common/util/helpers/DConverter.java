package com.dunkware.common.util.helpers;

public class DConverter {

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

}
