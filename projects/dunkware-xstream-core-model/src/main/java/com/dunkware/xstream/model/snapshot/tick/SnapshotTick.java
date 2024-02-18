package com.dunkware.xstream.model.snapshot.tick;

import java.io.ByteArrayInputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.msgpack.core.MessageBufferPacker;
import org.msgpack.core.MessagePack;
import org.msgpack.core.MessageUnpacker;
import org.msgpack.core.buffer.ArrayBufferInput;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.xstream.model.snapshot.SnapshotEntity;

public class SnapshotTick {

	public static final int TYPE_ENTITY_SNAPSHOT = -1; 
	public static final int TYPE_ENTITY_SIGNAL = -2; 
	
	public static final int FIELD_STREAM_ID = -3; 
	public static final int FIELD_ENTITY_ID = -4; 
	public static final int FIELD_TIMESTAMP = -5;
	public static final int FIELD_SIGNAL_ID = -6;
	
	public static void main(String[] args) {
		try {
			Map<Integer,Number> vars = new ConcurrentHashMap<Integer,Number>();
			vars.put(3, 32.3);
			vars.put(5, 32);
			//ZonedDateTime.of(LocalDate.of(2024, 1, 1),LocalTime.of(12, 30,3),DTimeZone.toZoneId(DTimeZone.NewYork))
			byte[] packed = packEntitySnapshot(3, 4,LocalDateTime.of(2024,1,1,9,30,5) ,vars);
				
			System.out.println(packed.length);
			unpackEntitySnapshot(packed);
			for (byte b : packed) {
				System.out.println(b);
			}
			test();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public static SnapshotEntity unpackEntitySnapshot(byte[] input) throws Exception { 
		 MessageUnpacker unpacker = MessagePack.newDefaultUnpacker(input);
		 int stream = unpacker.unpackInt();
		 int entity = unpacker.unpackInt();
		 long milliseconds = unpacker.unpackLong();
		 LocalDateTime dt = DunkTime.toLocalDateTime(milliseconds);
		 System.out.println(DunkTime.format(dt, DunkTime.YYYY_MM_DD_HH_MM_SS));
		 
		 int varCount = unpacker.unpackMapHeader();
		 Map<Integer,Number> vars = new ConcurrentHashMap<Integer, Number>();
		 for(int i = 0; i < varCount; i++) { 
			 Integer key = unpacker.unpackInt();
			 Double value = unpacker.unpackDouble();
			 vars.put(key, value);
		 }
		 SnapshotEntity ent = new SnapshotEntity();
		 ent.setEntity(entity);
		 ent.setNumericVars(vars);
		 
		 
		 return ent;
		 
		 
		 
	}
	
	public static void test() throws Exception { 
		MessageBufferPacker packer = MessagePack.newDefaultBufferPacker();
		packer.packInt(32).packString(DUUID.randomUUID(19)).packString(DUUID.randomUUID(19));
		byte[] ouot = packer.toByteArray();
		System.out.println(ouot.length);
	}
	
	public static byte[] packEntitySnapshot(int entityId, int streamId, LocalDateTime time, Map<Integer,Number> vars) throws Exception {
		
		MessageBufferPacker packer = MessagePack.newDefaultBufferPacker();
		
			packer.packInt(streamId);
			
			packer.packInt(entityId);
			packer.packLong(DunkTime.toMilliseconds(time));
			packer.packMapHeader(vars.size());	
			for (Integer varId : vars.keySet()) {
				packer.packInt(varId).packDouble(vars.get(varId).doubleValue());
			}
			return packer.toByteArray();
		
		
	}
}
