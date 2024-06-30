package com.dunkware.utils.tick.type;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;

import com.dunkware.utils.tick.TickException;
import com.dunkware.utils.tick.TickHelper;
import com.dunkware.utils.tick.proto.TickProto.Tick;
import com.dunkware.utils.tick.proto.TickProto.Tick.Builder;
import com.dunkware.utils.tick.proto.TickProto.Tick.TickField;
import com.dunkware.utils.tick.proto.TickProto.Tick.TickFieldType;

public class TimeTick {
	
	public static final int TYPE = 100;
	public static final int MINUTE = 2;
	public static final int HOUR = 1;
	public static final int SECOND = 3; 
	public static final int NANOSECOND = 4; 
	
	
	public static LocalTime decode(Tick tick) throws TickException { 
	  return LocalTime.of(TickHelper.getInt(tick, HOUR), TickHelper.getInt(tick, MINUTE), TickHelper.getInt(tick, SECOND), TickHelper.getInt(tick, NANOSECOND));
	  
	}
	
	public static Tick encode(LocalTime time) throws TickException { 
		Builder tickBuilder = Tick.newBuilder();
		tickBuilder.addTickFields(TickField.newBuilder().setIntValue(time.getHour()).setType(TickFieldType.INT).setId(HOUR).build());
		tickBuilder.addTickFields(TickField.newBuilder().setIntValue(time.getMinute()).setType(TickFieldType.INT).setId(MINUTE).build());
		tickBuilder.addTickFields(TickField.newBuilder().setIntValue(time.getSecond()).setType(TickFieldType.INT).setId(SECOND).build());
		tickBuilder.addTickFields(TickField.newBuilder().setLongValue(time.getNano()).setType(TickFieldType.LONG).setId(NANOSECOND).build());
		tickBuilder.setType(TYPE);
		return tickBuilder.build();
	}
	
	
	public static Tick encode(LocalDateTime time) {
		Builder tickBuilder = Tick.newBuilder();
		tickBuilder.addTickFields(TickField.newBuilder().setIntValue(time.get((ChronoField.HOUR_OF_DAY))).setType(TickFieldType.INT).setId(HOUR).build());
		tickBuilder.addTickFields(TickField.newBuilder().setIntValue(time.get(ChronoField.MINUTE_OF_HOUR)).setType(TickFieldType.INT).setId(MINUTE).build());
		tickBuilder.addTickFields(TickField.newBuilder().setIntValue(time.get(ChronoField.SECOND_OF_MINUTE)).setType(TickFieldType.INT).setId(SECOND).build());
		tickBuilder.addTickFields(TickField.newBuilder().setIntValue(time.get(ChronoField.MILLI_OF_SECOND)).setType(TickFieldType.INT).setId(NANOSECOND).build());
		tickBuilder.setType(TYPE);
		return tickBuilder.build();
	}

}
