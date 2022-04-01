package com.dunkware.common.tick.reactor.impl.updaters;

import com.dunkware.common.tick.reactor.TickReactorException;
import com.dunkware.common.tick.reactor.TickReactorRuntimeException;
import com.dunkware.common.tick.reactor.blueprint.ReactorUpdater;
import com.dunkware.common.tick.reactor.blueprint.updater.IncrementUpdaterType;
import com.dunkware.common.tick.reactor.impl.TickReactorTick;
import com.dunkware.common.tick.reactor.impl.TickReactorUpdater;

public class TickReactorIncrementUpdater implements TickReactorUpdater {

	private static final int KIND_NATURAL = 1;
	private static final int KIND_PERCENT = 2;

	private static final int OPERATOR_ADD = 1;

	private static final int FIELD_TYPE_INT = 1;
	private static final int FIELD_TYPE_DOUBLE = 2;
	private static final int FIELD_TYPE_LONG = 3;

	private IncrementUpdaterType type;
	private int[] fields = null;

	private int kind = 0;
	private int operator = 0;
	private int fieldType = 0;

	private double doubleIncrement = 0;
	private int intIncrement = 0;
	private long longIncrement = 0;

	// TickConstants.FIELD_TYPE_INT;

	@Override
	public void init(ReactorUpdater type) throws TickReactorException {
		this.type = (IncrementUpdaterType) type;
		String[] fieldValues = this.type.getFields().split(",");
		int i = 0;
		fields = new int[fieldValues.length];
		for (String fieldValue : fieldValues) {
			fields[i] = Integer.valueOf(fieldValue);
			i++;
		}
		boolean kindHandled = false;
		if (this.type.getKind().equals("natural")) {
			kind = KIND_NATURAL;
			kindHandled = true;
		}
		if (!kindHandled) {
			throw new TickReactorException("Exception init increment updator with kind " + this.type.getKind());
		}
		boolean operatorHandled = false;
		if (this.type.getOperator().equalsIgnoreCase("add")) {
			operator = OPERATOR_ADD;
			operatorHandled = true;
		}
		if (!operatorHandled) {
			throw new TickReactorException("Exception init increment updater with operator " + this.type.getOperator());
		}
		boolean fieldTypeHandled = false;
		if (this.type.getType().equalsIgnoreCase("Int")) {
			fieldType = FIELD_TYPE_INT;
			fieldTypeHandled = true;
			try {
				intIncrement = Integer.valueOf(this.type.getValue());
			} catch (RuntimeException e) {
				throw new TickReactorException("Exception casting increment value to int " + this.type.getValue());
			}

		}
		if (this.type.getType().equalsIgnoreCase("Double")) {
			fieldType = FIELD_TYPE_DOUBLE;
			fieldTypeHandled = true;
			try {
				doubleIncrement = Double.valueOf(this.type.getValue());
			} catch (RuntimeException e) {
				throw new TickReactorException("Exception casting increment value to double " + this.type.getValue());
			}
		}
		if (this.type.getType().equalsIgnoreCase("Long")) {
			fieldType = FIELD_TYPE_LONG;
			fieldTypeHandled = true;
			try {
				longIncrement = Long.valueOf(this.type.getValue());
			} catch (RuntimeException e) {
				throw new TickReactorException("Exception casting increment value to double " + this.type.getValue());
			}
		}
		if (!fieldTypeHandled) {
			throw new TickReactorException("Exception init increment updater with field type " + this.type.getType());
		}
	}

	@Override
	public boolean updateReactorTick(TickReactorTick tick) {
		for (int i : fields) {
			updateField(tick, i);
		}
		return true;
	}

	private void updateField(TickReactorTick tick, int fieldId) {
		switch (fieldType) {
		case FIELD_TYPE_INT:
			int intValue = tick.getInt(fieldId);
			int newValue = intValue + intIncrement;
			tick.setInt(fieldId, newValue);
			break;
		case FIELD_TYPE_DOUBLE:
			double doubleValue = tick.getDouble(fieldId);
			double newDouble = doubleValue + doubleIncrement;
			tick.setDouble(fieldId, newDouble);
			break;

		case FIELD_TYPE_LONG:
			long longValue = tick.getLong(fieldId);
			long newLong = longValue + longIncrement;
			tick.setLong(fieldId, newLong);
			break;
		default:
			throw new TickReactorRuntimeException("Field Type not handled in updateField method " + fieldType);
		}
	}

}
