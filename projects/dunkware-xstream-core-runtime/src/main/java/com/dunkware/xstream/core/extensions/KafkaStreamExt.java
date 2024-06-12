package com.dunkware.xstream.core.extensions;

import java.util.Properties;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer;
import com.dunkware.utils.kafka.byteconsumer.KafkaByteConsumer;
import com.dunkware.utils.kafka.byteconsumer.KafkaByteHandler;
import com.dunkware.utils.tick.proto.TickProto.Tick;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamException;
import com.dunkware.xstream.api.XStreamExtension;
import com.dunkware.xstream.core.annotations.AXStreamExtension;
import com.dunkware.xstream.xproject.model.XStreamExtensionType;

@AXStreamExtension(type = KafkaStreamExtType.class)
public class KafkaStreamExt implements XStreamExtension, KafkaByteHandler {

	private KafkaByteConsumer consumer;
	private XStream stream;
	private KafkaStreamExtType type;
	
	
	
	@Override
	public void init(XStream stream, XStreamExtensionType bean) throws XStreamException {
		this.stream = stream;
		this.type = (KafkaStreamExtType)bean;
		
	}

	@Override
	public void preStart() throws XStreamException {
		
		
	}

	@Override
	public void start() throws XStreamException {
		try {
			if(type.getDataTicks() != null) {
				String[] types = type.getDataTicks().split(",");
				for (String string : types) {
					String typeString = string.substring(0,string.indexOf(":"));
					String fieldString = string.substring(string.indexOf(":") + 1,string.length());
					Integer type = Integer.valueOf(typeString);
					Integer field = Integer.valueOf(fieldString);
					stream.getTickRouter().registerDataTick(type, field);
					
				}
			}
			// okay figure this kafka shit out 
			
			type.getProperties();
			consumer = DKafkaByteConsumer.newInstance(type.getProperties());
			consumer.addStreamHandler(this);
		} catch (Exception e) {
			throw new XStreamException("Exception connecting KafkaByteConsumer " + e.toString());
		}
	}
	
	

	@Override
	public void cancel() {
		consumer.dispose();
	}

	@Override
	public void preDispose() {
		consumer.dispose();
	}

	@Override
	public void dispose() {
	
		
	}

	@Override
	public void streamBytes(byte[] bytes) {
		try {
			// in the same vm ? 
			Tick tick = Tick.parseFrom(bytes);
			stream.getTickRouter().streamTick(tick);
		} catch (Exception e) {
			System.err.println("exception stream bytes " + e.toString());
		}
	}

	
	
}
