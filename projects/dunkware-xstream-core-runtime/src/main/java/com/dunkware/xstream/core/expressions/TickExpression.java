package com.dunkware.xstream.core.expressions;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import com.dunkware.common.tick.TickHandler;
import com.dunkware.common.tick.TickHelper;
import com.dunkware.common.tick.filter.TickFilterBuilder;
import com.dunkware.common.tick.proto.TickProto.Tick;
import com.dunkware.common.tick.proto.TickProto.Tick.TickField;
import com.dunkware.common.tick.proto.TickProto.Tick.TickFieldType;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.core.XStreamExpressionImpl;
import com.dunkware.xstream.core.annotations.AXStreamExpression;
import com.dunkware.xstream.xScript.DataType;
import com.dunkware.xstream.xScript.ExpressionType;
import com.dunkware.xstream.xScript.TickExpressionType;

@AXStreamExpression(type = TickExpressionType.class)
public class TickExpression extends XStreamExpressionImpl implements TickHandler {

	private XStreamRow row;
	private TickExpressionType type;

	private int tickType;
	private int tickField;
	private int tickFieldDataType;

	private Object lastValue = null;

	@Override
	public void init(XStreamRow row, ExpressionType expType) {
		this.row = row;
		this.type = (TickExpressionType) expType;
		this.tickType = this.type.getType();
		this.tickField = this.type.getField();
		this.tickFieldDataType = this.type.getDataType().getValue();
	}

	@Override
	public void start() {
		// add tick handler for specified tick type
		row.getTickStream().addTickHandler(this, TickFilterBuilder.typeIncludeFilter(tickType));
	}

	@Override
	public void dispose() {
		row.getTickStream().removeTickHandler(this);
	}

	@Override
	public ExpressionType getExpType() {
		return type;
	}

	@Override
	public boolean execute() {
		return false;
	}

	@Override
	public boolean canExecute() {
		return true;
	}

	@Override
	public boolean isInput() {
		return true;
	}

	@Override
	public void onTick(Tick tick) {
		TickField field = null;
		try {
			field = TickHelper.getField(tick, tickField);
		} catch (RuntimeException e) {
			throw new XStreamRuntimeException("Tick Expression onTick() TichHelper.getField(" + tick.getType() + ","
					+ tickField + ")" + e.toString(), e);
		}

		if (tickFieldDataType == DataType.DUB_VALUE) {
			Double dubValue = field.getDoubleValue();
			BigDecimal bd = new BigDecimal(Double.toString((Double)dubValue), MathContext.DECIMAL64);
			bd.setScale(2, RoundingMode.UP);
			dubValue = bd.doubleValue();
			setValue(dubValue);

		}
		if (tickFieldDataType == DataType.STR_VALUE) {
			if (field.getType() != TickFieldType.STRING) {
				throw new XStreamRuntimeException(
						"Tick Expression (" + type.getType() + "," + type.getField() + ")" + " is not type string");
			}
			setValue(field.getStringValue());
			return;
		}
		if (tickFieldDataType == DataType.INT_VALUE) {
			setValue(field.getIntValue());
			return;
		}
		if (tickFieldDataType == DataType.LONG_VALUE) {
			setValue(field.getLongValue());
			return;

		}
	}

}
