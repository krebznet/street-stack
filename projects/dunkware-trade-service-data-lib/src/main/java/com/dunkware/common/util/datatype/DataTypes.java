package com.dunkware.common.util.datatype;

public class DataTypes {

	public static final int DOUBLE = 0;
	public static final int INTEGER = 1;
	public static final int LONG = 2;
	public static final int STRING = 3;
	public static final int TIME = 4;
	public static final int DATE = 5;
	public static final int DATETIME = 6;
	public static final int BOOLEAN = 7;
	public static final int BYTEARRAY = 8;


	/**
	 * Returns the DataType Literal Name
	 * 
	 * @param dataType
	 * @return
	 * @throws DataTypeException
	 */
	public static String getLiteral(int dataType) {
		switch (dataType) {
		case DOUBLE:
			return "Double";
		case INTEGER:
			return "Integer";
		case LONG:
			return "Long";
		case STRING:
			return "String";
		case TIME:
			return "Time";
		case DATE:
			return "Date";
		case DATETIME:
			return "DateTime";
		case BOOLEAN:
			return "Boolean";
		case BYTEARRAY:
			return "ByteArray";
			
		default:
			return "Unknown";

		}
	}

	/**
	 * Returns true if the value passed in is a valid data type
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isDataType(Object obj) {
		int type = getDataType(obj);
		if (type != -1) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the DataType of a value, throws Exception if not a known data type
	 * 
	 * @param obj
	 * @return
	 * @throws DataTypeException
	 */
	public static int getDataType(Object obj) {
		if (obj instanceof Double) {
			return DOUBLE;
		}
		if (obj instanceof Integer) {
			return INTEGER;
		}
		if (obj instanceof Long) {
			return LONG;
		}
		if (obj instanceof String) {
			return STRING;
		}
		/*
		 * if (obj instanceof DDateTime) { return DATETIME; } if (obj instanceof DTime)
		 * { return TIME; } if (obj instanceof DDate) { return DATE; }
		 */
		if (obj instanceof Boolean) {
			return BOOLEAN;
		}
		if (obj instanceof byte[]) {
			return BYTEARRAY;
			
		}
		return -1;
	}

}
