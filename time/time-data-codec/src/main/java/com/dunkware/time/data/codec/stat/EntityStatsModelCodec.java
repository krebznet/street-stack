package com.dunkware.time.data.codec.stat;

import java.io.IOException;
import java.time.LocalTime;

import org.msgpack.core.MessageBufferPacker;
import org.msgpack.core.MessagePack;
import org.msgpack.core.MessageUnpacker;

import com.dunkware.time.data.codec.time.LocalDateCodec;
import com.dunkware.time.data.codec.time.LocalTimeCodec;
import com.dunkware.time.data.model.entity.EntityStatsModel;
import com.dunkware.time.data.model.entity.EntityVarStatsModel;

public class EntityStatsModelCodec {

	public static byte[] encode(EntityStatsModel input) throws IOException {
		MessageBufferPacker packer = MessagePack.newDefaultBufferPacker();
		packer.packInt(input.getStream());
		packer.packInt(input.getEntity());
		packer.packLong(LocalDateCodec.encode(input.getDate()));
		// signal counts
		packer.packMapHeader(input.getSigcounts().size());
		for (Integer sig : input.getSigcounts().keySet()) {
			packer.packInt(sig);
			packer.packInt(input.getSigcounts().get(sig));
		}
		// var stats
		packer.packInt(input.getVarstats().size());
		for (int varId : input.getVarstats().keySet()) {
			packer.packInt(varId);
			EntityVarStatsModel statsVar = input.getVarstats().get(varId);
			packer.packMapHeader(statsVar.getStats().size());
			for (Integer stat : statsVar.getStats().keySet()) {
				packer.packInt(stat);
				packer.packDouble(statsVar.getStats().get(stat));
			}
			packer.packMapHeader(statsVar.getTimes().size());
			for (Integer stat : statsVar.getTimes().keySet()) {
				packer.packInt(stat);
				packer.packLong(LocalTimeCodec.encode(statsVar.getTimes().get(stat)));
			}

		}
		return packer.toByteArray();

	}

	public static EntityStatsModel decode(byte[] input) throws IOException {
		EntityStatsModel eds = new EntityStatsModel();
		MessageUnpacker unpacker = MessagePack.newDefaultUnpacker(input);
		eds.setStream(unpacker.unpackInt());
		eds.setEntity(unpacker.unpackInt());
		eds.setDate(LocalDateCodec.decode(unpacker.unpackLong()));
		int sigCountSize = unpacker.unpackMapHeader();
		for(int i = 0; i < sigCountSize; i++) { 
			int signal = unpacker.unpackInt();
			int count = unpacker.unpackInt();
			eds.getSigcounts().put(signal, count);
		}
		int varStatSize = unpacker.unpackInt();
		for(int i = 0; i < varStatSize; i++) { 
			int varId = unpacker.unpackInt();
			EntityVarStatsModel dateStatsVar = new EntityVarStatsModel();
			dateStatsVar.setVar(varId);
			int varStatCount = unpacker.unpackMapHeader();
			for(int x = 0; x < varStatCount; x++) { 
				int stat = unpacker.unpackInt();
				double value = unpacker.unpackDouble();
				dateStatsVar.getStats().put(stat, value);
			}
			int statTimeCount = unpacker.unpackMapHeader();
			for(int x = 0; x < statTimeCount; x++) { 
				int stat = unpacker.unpackInt();
				LocalTime time = LocalTimeCodec.decode(unpacker.unpackLong());		
				dateStatsVar.getTimes().put(stat, time);
			}
			eds.getVarstats().put(varId, dateStatsVar);
		}
		return eds;
	}
	

}
