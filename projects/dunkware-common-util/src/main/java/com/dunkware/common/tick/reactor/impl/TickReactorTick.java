package com.dunkware.common.tick.reactor.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.common.tick.proto.TickProto.Tick;
import com.dunkware.common.tick.proto.TickProto.Tick.TickField;
import com.dunkware.common.tick.proto.TickProto.Tick.TickField.Builder;
import com.dunkware.common.tick.proto.TickProto.Tick.TickFieldType;
import com.dunkware.common.tick.reactor.TickReactorRuntimeException;

public class TickReactorTick {

	private int type;
	private ConcurrentHashMap<Integer, TickReactorTickField> fields = new ConcurrentHashMap<Integer, TickReactorTickField>();

	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public void addField(Integer id, TickReactorTickField field) {
		this.fields.put(id, field);
	}

	public void setString(int fieldId, String value) {
		if(!fields.containsKey(fieldId)) { 
			throw new TickReactorRuntimeException("Tick Reactor Field " + fieldId + " not found");
		}
		TickReactorTickField field = fields.get(fieldId);
		TickFieldType type = field.getType();
		if(type != TickFieldType.STRING) { 
			throw new TickReactorRuntimeException("set string on field is not string field id " + fieldId);
		}
		field.setStringValue(value);
		
	}

	public void setInt(int fieldId, int value) {
		if(!fields.containsKey(fieldId)) { 
			throw new TickReactorRuntimeException("Tick Reactor Field " + fieldId + " not found");
		}
		TickReactorTickField field = fields.get(fieldId);
		TickFieldType type = field.getType();
		if(type != TickFieldType.INT) { 
			throw new TickReactorRuntimeException("set int on field is not int field id " + fieldId);
		}
		field.setIntValue(value);
	}

	public void setDouble(int fieldId, double value) {
		if(!fields.containsKey(fieldId)) { 
			throw new TickReactorRuntimeException("Tick Reactor Field " + fieldId + " not found");
		}
		TickReactorTickField field = fields.get(fieldId);
		TickFieldType type = field.getType();
		if(type != TickFieldType.DOUBLE) { 
			throw new TickReactorRuntimeException("set double on field is not double field id " + fieldId);
		}
		field.setDoubleValue(value);
	}
	
	public void setLong(int fieldId, long value) { 
		if(!fields.containsKey(fieldId)) { 
			throw new TickReactorRuntimeException("Tick Reactor Field " + fieldId + " not found");
		}
		TickReactorTickField field = fields.get(fieldId);
		TickFieldType type = field.getType();
		if(type != TickFieldType.LONG) { 
			throw new TickReactorRuntimeException("set long on field is not long field id " + fieldId);
		}
		field.setLongValue(value);
		
	}
	
	public String getString(int fieldId) { 
		if(!fields.containsKey(fieldId)) { 
			throw new TickReactorRuntimeException("Tick Reactor Field " + fieldId + " not found");
		}
		TickReactorTickField field = fields.get(fieldId);
		TickFieldType type = field.getType();
		if(type != TickFieldType.STRING) { 
			throw new TickReactorRuntimeException("get string value on field is not string field id " + fieldId);
		}
		return field.getStringValue();

	}
	
	public long getLong(int fieldId) { 
		if(!fields.containsKey(fieldId)) { 
			throw new TickReactorRuntimeException("Tick Reactor Field " + fieldId + " not found");
		}
		TickReactorTickField field = fields.get(fieldId);
		TickFieldType type = field.getType();
		if(type != TickFieldType.LONG) { 
			throw new TickReactorRuntimeException("get long on field is not long field id " + fieldId);
		}
		return field.getLongValue();
	}
	
	public double getDouble(int fieldId) { 
		if(!fields.containsKey(fieldId)) { 
			throw new TickReactorRuntimeException("Tick Reactor Field " + fieldId + " not found");
		}
		TickReactorTickField field = fields.get(fieldId);
		TickFieldType type = field.getType();
		if(type != TickFieldType.DOUBLE) { 
			throw new TickReactorRuntimeException("get double on field is not double field id " + fieldId);
		}
		return field.getDoubleValue();
	}
	
	public int getInt(int fieldId) { 
		if(!fields.containsKey(fieldId)) { 
			throw new TickReactorRuntimeException("Tick Reactor Field " + fieldId + " not found");
		}
		TickReactorTickField field = fields.get(fieldId);
		TickFieldType type = field.getType();
		if(type != TickFieldType.INT) { 
			throw new TickReactorRuntimeException("get int on field is not int field id " + fieldId);
		}
		return field.getIntValue();
	}
	
	/**
	 * Creates a Tick from this model;
	 * @return
	 */
	public Tick createTick() { 
		List<TickField> tickFields = new ArrayList<TickField>();
		for (TickReactorTickField reactorField : fields.values()) {
			Builder fieldBuilder = TickField.newBuilder();
			fieldBuilder.setType(reactorField.getType());
			fieldBuilder.setId(reactorField.getId());
			boolean fieldHandled = false;
			if(reactorField.getType() == TickFieldType.INT) { 
				fieldBuilder.setIntValue(reactorField.getIntValue());
				fieldHandled = true;
			}
			if(reactorField.getType() == TickFieldType.DOUBLE) { 
				fieldBuilder.setDoubleValue(reactorField.getDoubleValue());
				fieldHandled = true;
			}
			if(reactorField.getType() == TickFieldType.LONG) { 
				fieldBuilder.setLongValue(reactorField.getLongValue());
				fieldHandled = true;
			}
			if(reactorField.getType() == TickFieldType.STRING) { 
				fieldBuilder.setStringValue(reactorField.getStringValue());
				fieldHandled = true;
			}
			if(!fieldHandled) { 
				throw new TickReactorRuntimeException("creatTick() method field type " + reactorField.getType().name() + " not handled");
			}
			tickFields.add(fieldBuilder.build());
		}
		
		Tick tick = Tick.newBuilder().addAllTickFields(tickFields).setType(this.getType()).build();
		return tick;
	}
}
