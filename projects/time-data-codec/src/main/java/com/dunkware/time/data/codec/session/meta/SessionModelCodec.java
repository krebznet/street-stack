package com.dunkware.time.data.codec.session.meta;

import java.io.IOException;

import org.msgpack.core.MessageBufferPacker;
import org.msgpack.core.MessagePack;
import org.msgpack.core.MessageUnpacker;

import com.dunkware.time.data.codec.time.LocalDateCodec;
import com.dunkware.time.data.codec.time.LocalTimeCodec;
import com.dunkware.time.data.model.session.StreamSessionModel;

public class SessionModelCodec {
	
	public static byte[] encode(StreamSessionModel input) throws IOException {
		MessageBufferPacker packer = MessagePack.newDefaultBufferPacker();
		packer.packInt(input.getStream());
		
		packer.packLong(LocalDateCodec.encode(input.getDate()));
		packer.packLong(LocalTimeCodec.encode(input.getStart()));
		packer.packLong(LocalTimeCodec.encode(input.getStop()));
		packer.packInt(input.getEntities().size());
		for (Integer ent : input.getEntities()) {
			packer.packInt(ent);
		}
		packer.packInt(input.getVars().size()); 
		for (Integer var : input.getVars()) {
			packer.packInt(var);
		}
		packer.packInt(input.getSignals().size());
		for (Integer signal : input.getSignals()) {
			packer.packInt(signal);
		}
		packer.packInt(input.getStats().size());
		for (Integer stat : input.getStats()) {
			packer.packInt(stat);
		}
		
		return packer.toByteArray();
	}
	
	
	public static StreamSessionModel decode(byte[] input) throws Exception {
		StreamSessionModel entity = new StreamSessionModel();
		MessageUnpacker unpacker = MessagePack.newDefaultUnpacker(input);
		entity.setStream(unpacker.unpackInt());
		entity.setDate(LocalDateCodec.decode(unpacker.unpackLong()));
		entity.setStart(LocalTimeCodec.decode(unpacker.unpackLong()));
		entity.setStop(LocalTimeCodec.decode(unpacker.unpackLong()));
		int entityCount = unpacker.unpackInt();
		for(int i = 0; i < entityCount; i++) {
			entity.getEntities().add(unpacker.unpackInt());
		}
		int varCount = unpacker.unpackInt();
		for(int i = 0; i < varCount; i++) {
			entity.getVars().add(unpacker.unpackInt());
		}
		int signalCount = unpacker.unpackInt();
		for(int i = 0; i < signalCount; i++) {
			entity.getSignals().add(unpacker.unpackInt());
		}
		int statCount = unpacker.unpackInt();
		for(int i = 0; i < statCount; i++) {
			entity.getStats().add(unpacker.unpackInt());
		}
		return entity;
		
		
	}

}
