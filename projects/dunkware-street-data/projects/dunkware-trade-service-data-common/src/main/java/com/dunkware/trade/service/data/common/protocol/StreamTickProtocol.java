package com.dunkware.trade.service.data.common.protocol;

import com.dunkware.common.tick.proto.TickProto;
import com.dunkware.common.tick.proto.TickProto.Tick;
import com.dunkware.common.tick.proto.TickProto.Tick.TickField;
import com.dunkware.xstream.model.snapshot.SnapshotEntity;

public class StreamTickProtocol {

	public static final int MESSAGE_ENTITY_SNAPAHOT = 1; 
	public static final int MESSAGE_ENTITY_SIGNAL = 2; 
	
	public static final int FIELD_STREAM_ID = 1; 
	public static final int FIELD_ENTITY_ID = 2; 
	public static final int FIELD_VAR_ID = 3; 
	
	public static Tick buildEntitySnapshotTIck()  {
		return null;
	}
	
	public static Tick encodeEntitySnapshot(SnapshotEntity entity) { 
		TickProto.Tick.Builder builder = TickProto.Tick.newBuilder().setType(MESSAGE_ENTITY_SNAPAHOT);
		builder.addTickFields(TickField.newBuilder().setId(FIELD_STREAM_ID).setIntValue(entity.getStream()));
		//builder.addTick
		return builder.build();
		
	}
	
	
	
	// its just publishing the tick protocol to a a fuckin topic in kafka 
	// its just starting an injestor that writes the shit to the database. 
	
}
