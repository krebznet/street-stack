package com.dunkware.utils.tick;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.utils.tick.proto.TickProto.Tick;
import com.dunkware.utils.tick.proto.TickProto.Tick.TickField;
import com.dunkware.utils.tick.proto.TickProto.Tick.TickFieldType;

public class TickHelper {

	
	private static Logger logger = LoggerFactory.getLogger(TickHelper.class);

	public static TickField getField(Tick tick, int id) throws TickException {
		for (TickField field : tick.getTickFieldsList()) {
			if (field.getId() == id) {
				return field;
			}
		}
		throw new TickException("Tick Helper getField() id " + id + " not found on field");
	}

	public static int getInt(Tick tick, int fieldId) throws TickException {
		TickField field = getField(tick, fieldId);
		if (field.getType() != TickFieldType.INT) {
			throw new TickException("invalid get int field type " + field.getType().name());
		}
		return getField(tick, fieldId).getIntValue();
	}

	public static double getDouble(Tick tick, int fieldId) throws TickException {
		return getField(tick, fieldId).getDoubleValue();
	}

	public static String getString(Tick tick, int fieldId) throws TickException {
		return getField(tick, fieldId).getStringValue();
	}

	public static long getLong(Tick tick, int fieldId) throws TickException {
		return getField(tick, fieldId).getLongValue();
	}


	public static String printTick(Tick tick) {
		StringBuilder b = new StringBuilder();
		
		b.append(tick.getType());
		b.append(":");
		b.append(tick.getTickFieldsCount());
		for (TickField field : tick.getTickFieldsList()) {
			b.append("[");
			b.append(field.getId());
			b.append("=");
			if (field.getType() == TickFieldType.INT) {
				b.append(field.getIntValue());
			}
			if (field.getType() == TickFieldType.DOUBLE) {
				b.append(field.getDoubleValue());
			}
			if (field.getType() == TickFieldType.LONG) {
				b.append(field.getLongValue());
			}
			if (field.getType() == TickFieldType.STRING) {
				b.append(field.getStringValue());
			}
			b.append("]");
			
		}

		return b.toString();
	}
}
