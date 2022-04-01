package com.dunkware.common.util.datatype;

public interface DataTypeSwitch {

	public void typeDouble() throws Exception;

	public void typeString() throws Exception;

	public void typeInteger() throws Exception;

	public void typeLong() throws Exception;

	public void typeDTime() throws Exception;

	public void typeDDate() throws Exception;

	public void typeDDateTime() throws Exception;
	
	public void typeBoolean() throws Exception; 
	
	public void typeByteArray() throws Exception;
	
	public void typeUnknown() throws Exception;
	
	public default void doSwitch(int type) throws Exception {
		

		switch (type) {
		case DataTypes.DATE:
			typeDDate();
			break;
		case DataTypes.DATETIME:
			typeDDateTime();
			break;
		case DataTypes.DOUBLE:
			typeDouble();
			break;
		case DataTypes.TIME:
			typeDTime();
			break;
		case DataTypes.INTEGER:
			typeInteger();
			break;
		case DataTypes.LONG:
			typeLong();
			break;
		case DataTypes.STRING:
			typeString();
			break;
		case DataTypes.BOOLEAN:
			typeBoolean();
			break;
		case DataTypes.BYTEARRAY:
			typeByteArray();
			break;
		default:
			typeUnknown();
			break;
			
		}

	}
}
