package com.dunkware.utils.tick;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.utils.tick.proto.TickProto.Tick;
import com.dunkware.utils.tick.proto.TickProto.Tick.TickField;
import com.dunkware.utils.tick.proto.TickProto.Tick.TickFieldType;

public class TickBuilder {
	
	public static TickBuilder newBuilder(int type) { 
		return new TickBuilder(type);
	}
	
	private int type; 
	private List<TickField> fields = new ArrayList<TickField>();
	
 	private TickBuilder(int type) { 
		this.type = type;
	}
	
	public TickBuilder doubleField(int id, double value) { 
		fields.add(TickField.newBuilder().setDoubleValue(value).setType(TickFieldType.DOUBLE).setId(id).build());
		return this;
	}
	
	public TickBuilder intField(int id, int value) { 
		fields.add(TickField.newBuilder().setIntValue(value).setType(TickFieldType.INT).setId(id).build());
		return this;
	}
	
	public TickBuilder longField(int id, long value) { 
		fields.add(TickField.newBuilder().setLongValue(value).setType(TickFieldType.LONG).setId(id).build());
		return this;
	}
	
	public TickBuilder stringField(int id, String value) { 
		fields.add(TickField.newBuilder().setStringValue(value).setType(TickFieldType.STRING).setId(id).build());
		return this;
	}
	
	public Tick build() { 
		return Tick.newBuilder().setType(type).addAllTickFields(fields).build();
	}

}
