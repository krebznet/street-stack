package com.dunkware.utils.tick.reactor.impl;

import org.apache.commons.beanutils.ConvertUtils;

import com.dunkware.utils.tick.proto.TickProto.Tick.TickField;
import com.dunkware.utils.tick.proto.TickProto.Tick.TickFieldType;
import com.dunkware.utils.tick.proto.TickProto.Tick.TickField.Builder;
import com.dunkware.utils.tick.reactor.TickReactorRuntimeException;

public class TickReactorTickField {

	private TickFieldType type;
	private Double doubleValue = null;
	private Long longValue = null;
	private String stringValue = null;
	private Integer intValue = null;
	private int id;

	public TickFieldType getType() {
		return type;
	}

	public void setType(TickFieldType type) {
		this.type = type;
	}

	public Double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(Double dubValue) {
		doubleValue = dubValue;
	}

	public Long getLongValue() {
		return longValue;
	}

	public void setLongValue(Long longValue) {
		this.longValue = longValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public Integer getIntValue() {
		return intValue;
	}

	public void setIntValue(Integer intValue) {
		this.intValue = intValue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Throws TickReactorRuntimeException if the value cannot be auto set. You
	 * should use Commons data converter.
	 * 
	 * @param value
	 */
	public void autoSetValue(Object value) {
		//(double[])ConvertUtils.convert(values, Double.TYPE);
		if (type == TickFieldType.DOUBLE) {
			try {
				Double dubValue = (Double)ConvertUtils.convert(value, Double.TYPE);
				this.doubleValue = dubValue;
			} catch (Exception e) {
				throw new TickReactorRuntimeException("autoSetFieldValue double exception " + e.toString(),e);
			}
		}
		
		if (type == TickFieldType.INT) {
			try {
				Integer integerValue = (Integer)ConvertUtils.convert(value, Integer.TYPE);
				this.intValue = integerValue;
			} catch (Exception e) {
				throw new TickReactorRuntimeException("autoSetFieldValue int exception " + e.toString(),e);
			}
			
		}
		
		if (type == TickFieldType.LONG) {
			try {
				Long longerValue = (Long)ConvertUtils.convert(value, Long.TYPE);
				this.longValue = longerValue;
			} catch (Exception e) {
				throw new TickReactorRuntimeException("autoSetFieldValue int exception " + e.toString(),e);
			}

		}
		
		if (type == TickFieldType.STRING) {
			this.stringValue = value.toString();
		}
		throw new TickReactorRuntimeException("autoSetValue failed not handled type " + type.name());
	}

	public TickField createTickField() {
		Builder builder = TickField.newBuilder();
		builder.setType(type);
		builder.setId(id);
		if (type == TickFieldType.DOUBLE) {
			if (doubleValue == null) {
				throw new TickReactorRuntimeException("Proto Tick Convert Double value is null for type");
			}
			builder.setDoubleValue(doubleValue);
			return builder.build();

		}
		if (type == TickFieldType.INT) {
			
			if (intValue == null) {
				throw new TickReactorRuntimeException("Proto Tick Convert Int value is null for type");
			}
			builder.setIntValue(intValue);
			return builder.build();

		}
		if (type == TickFieldType.LONG) {
			if (longValue == null) {
				throw new TickReactorRuntimeException("Proto Tick Convert Long value is null for type");
			}
			builder.setLongValue(longValue);
			return builder.build();

		}
		if (type == TickFieldType.STRING) {
			if(stringValue == null) { 
				throw new TickReactorRuntimeException("Proto Tick Convert String value is null for type");
			}
			builder.setStringValue(stringValue);
			return builder.build();
		}
		throw new TickReactorRuntimeException("Protoc Tick Convert Value Type Not Handled Check Code!");
	}

}
