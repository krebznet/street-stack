package com.dunkware.common.util.datatype;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.dtime.DTime;

public interface DataValueSwitch {

	public void typeDouble(Double value) throws Exception;

	public void typeString(String value) throws Exception;

	public void typeInteger(Integer value) throws Exception;

	public void typeLong(Long value) throws Exception;

	public void typeDTime(DTime value) throws Exception;

	public void typeDDate(DDate value) throws Exception;

	public void typeDDateTime(DDateTime value) throws Exception;

	public void typeBoolean(Boolean value) throws Exception;

	public void typeByteArray(byte[] value) throws Exception;

	public default void doSwitch(Object value) throws Exception {
		if (!DataTypes.isDataType(value)) {
			throw new Exception("Object " + value.getClass().getName() + " is not a valid data type");
		}
		int type = DataTypes.getDataType(value);

		switch (type) {
		case DataTypes.DATE:
			typeDDate((DDate) value);
			break;
		case DataTypes.DATETIME:
			typeDDateTime((DDateTime) value);
			break;
		case DataTypes.DOUBLE:
			typeDouble((Double) value);
			break;
		case DataTypes.TIME:
			typeDTime((DTime) value);
			break;
		case DataTypes.INTEGER:
			typeInteger((Integer) value);
			break;
		case DataTypes.LONG:
			typeLong((Long) value);
			break;
		case DataTypes.STRING:
			typeString((String) value);
			break;
		case DataTypes.BOOLEAN:
			typeBoolean((Boolean) value);
			break;
		case DataTypes.BYTEARRAY:
			typeByteArray((byte[]) value);
			break;
		default:
			throw new Exception("Unknown Data Type " + type);
		}

	}
}
