package com.dunkware.net.core.messaging.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.common.util.json.DJson;
import com.dunkware.net.core.messaging.DException;
import com.dunkware.net.core.messaging.DMessage;
import com.dunkware.net.core.runtime.core.helpers.GProtoHelper;
import com.dunkware.net.core.runtime.proto.core.DateTime;
import com.dunkware.net.proto.message.GBytesValue;
import com.dunkware.net.proto.message.GDateTimeValue;
import com.dunkware.net.proto.message.GDoubleValue;
import com.dunkware.net.proto.message.GIntValue;
import com.dunkware.net.proto.message.GJsonValue;
import com.dunkware.net.proto.message.GMessage;
import com.dunkware.net.proto.message.GProperty;
import com.dunkware.net.proto.message.GTimeValue;
import com.dunkware.net.proto.message.GValue;
import com.google.protobuf.ByteString;

public class DMessageImpl  implements DMessage {
	
	private GMessage protoMessage = null;
	private Map<String,GProperty> properties = new ConcurrentHashMap<String,GProperty>();
	private int messageId = DRandom.getRandom(1, 100000);
	private int callbackMessageId = -1; 

	
	@Override
	public int getMessageId() {
		return messageId;
		
	}

	@Override
	public void setCallbackid(int id) {
		callbackMessageId = id;
		
	}

	@Override
	public void setInt(String key, Integer value) {
		properties.put(key, GProperty.newBuilder().setName(key).setValue(GValue.newBuilder().setInt(GIntValue.newBuilder().setValue(value).build()).build()).build());
		
	}

	@Override
	public void setDouble(String key, Double value) {
		properties.put(key, GProperty.newBuilder().setName(key).setValue(GValue.newBuilder().setDouble(GDoubleValue.newBuilder().setValue(value).build()).build()).build());
			
	}

	
	@Override
	public void setDateTime(String key, LocalDateTime dateTime) {
		
		properties.put(key, GProperty.newBuilder().setName(key).setValue(GValue.newBuilder().
				setDateTime(GDateTimeValue.newBuilder().setValue(GProtoHelper.toTimeStamp(dateTime, DTimeZone.NewYork)).build()).build()).build());
		
		
	}

	@Override
	public void setTime(String key, LocalTime time) {
		properties.put(key, GProperty.newBuilder().setName(key).setValue(GValue.newBuilder().
				setTime(GTimeValue.newBuilder().setValue(GProtoHelper.toTimeStamp(LocalDateTime.of(LocalDate.now(), time), DTimeZone.NewYork)).build()).build()).build());
	}

	@Override
	public void setBytes(String key, byte[] bytes) {
		properties.put(key, GProperty.newBuilder().setName(key).setValue(GValue.newBuilder()
				.setBytes(GBytesValue.newBuilder().setValue(ByteString.copyFrom(bytes)).build()).build()).build());
		
	}

	@Override
	public void setJson(String key, Object json) throws DException {
		String ser = null;
		try {
			ser = DJson.serialize(json);
			properties.put(key, GProperty.newBuilder().setName(key).setValue(GValue.newBuilder().setJson(GJsonValue.newBuilder().setValue(ser).build()).build()).build());
					
		} catch (Exception e) {
			throw new DException("Exception serializzing json object " + json.getClass().getName() + " " + e.toString());
		}
		
	}

	@Override
	public DateTime getDateTime(String key) throws DException {
	
		return null;
	}

	@Override
	public String getString(String key) throws DException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getDouble(String key) throws DException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getBytes(String key) throws DException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getJson(String key, Class type) throws DException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public GMessage toProto() throws DException { 
		return GMessage.newBuilder().setMessageId(messageId).addAllProperties(this.properties.values()).build();
	}
	
	

}
