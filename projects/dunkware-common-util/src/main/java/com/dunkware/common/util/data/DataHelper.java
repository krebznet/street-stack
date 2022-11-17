package com.dunkware.common.util.data;

public class DataHelper {

	public static DataType getDataType(Object input) throws Exception {
		if (input instanceof Double) {
			return DataType.DOUBLE;
		}
		if (input instanceof Long) {
			return DataType.LONG;
		}
		if (input instanceof Integer) {
			return DataType.INT;
		}
		if (input instanceof String) {
			return DataType.STRING;
		}
		if (input instanceof Boolean) {
			return DataType.BOOL;
		}
		throw new Exception("Object type " + input.getClass().getName() + " not valid data type");
	}

	public static int compareData(Object v1, Object v2) throws Exception {
		Double compare1 = convertToDouble(v1); 
		Double compare2 = convertToDouble(v2);
		return compare1.compareTo(compare2);
	}
	
	public static Double convertToDouble(Object input) throws Exception { 
		DataType dt = getDataType(input);
		if(dt == DataType.BOOL) { 
			throw new Exception("Cannot convert Boolean to double");
		}
		if(dt == DataType.DOUBLE) { 
			return (Double)input;
		}
		if(dt == DataType.LONG) { 
			Long v = (Long)input;
			return v.doubleValue();
		}
		if(dt == DataType.INT) { 
			Integer v = (Integer)input;
			return v.doubleValue();
		}
		if(dt == DataType.STRING) { 
			throw new Exception("Cannot convert string to double" );
		}
		throw new Exception("Data Type " + dt.name() + " not handled in to double");
		
	}

}
