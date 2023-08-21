package com.dunkware.spring.messaging.message;

import java.beans.Transient;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.spring.messaging.DunkNetException;

public class DunkNetMessage {

	public static Builder builder() {
		return new Builder();
	}
	
	public static Builder builder(String channel) { 
		Builder b = new Builder();
		b.channel(channel);
		return b;
	}

	public static class Builder {

		
		private DunkNetMessage m = new DunkNetMessage();

		public Builder serviceRequest(Object payload) { 
			m.setType(TYPE_SERVICE_REQUEST);
			m.setPayload(payload);
			return this;
		}
		
		public Builder serviceRequest(Object payload, String channel) { 
			m.setType(TYPE_SERVICE_REQUEST);
			m.setPayload(payload);
			m.setChannel(channel);
			return this;
		}
		
		public Builder serviceResponse(Object payload, String requestId) {  
			m.setType(TYPE_SERVICE_RESPONSE);
			m.setPayload(payload);
			return this;
		}
		
		public Builder serviceException(Object payload, String requestId, String exception) {  
			m.setType(TYPE_SERVICE_RESPONSE);
			m.setPayload(payload);
			m.setHeader(KEY_RESPONSE_CODE, RESPONSE_ERROR);
			m.setHeader(KEY_RESPONSE_ERROR, exception);
			return this;
		}
		
		public Builder channelResponse(Object response, String channelId, String requestId) { 
			m.setType(TYPE_CHANNEL_RESPONSE);
			m.setHeader(KEY_REQUEST_ID, requestId);
			m.setHeader(KEY_CHANNEL_ID,channelId);
			m.setPayload(response);
			return this;
		}
		
		public Builder channelException(String channelId, String requestId, String error) { 
			m.setType(TYPE_CHANNEL_RESPONSE);
			m.setHeader(KEY_REQUEST_ID, requestId);
			m.setHeader(KEY_CHANNEL_ID,channelId);
			m.setHeader(KEY_RESPONSE_ERROR, error);
			return this;
		}
		
		public Builder event(Object payload) {
			m.setType(TYPE_EVENT);
			m.setPayload(payload);
			return this;
		}
		
		public Builder event(Object payload, String channel) {
			m.setType(TYPE_EVENT);
			m.setPayload(payload);
			m.setChannel(channel);
			return this;
		}


		public Builder channel(String id) {
			m.setChannel(id);
			;
			return this;
		}

		public Builder setHeader(String key, Object value) {
			m.getHeaders().put(key, value);
			return this;
		}

		public DunkNetMessage buildMessage() {
			return m;
		}

		public DunkNetMessageTransport buildTransport(String senderId) throws DunkNetException {
			return DunkNetMessageHelper.toTransport(m,senderId);
		}
	}

	public static DunkNetMessage transport(DunkNetMessageTransport transport) throws DunkNetException {
		Object payload = null;
		if (transport.getPayload() != null) {
			try {
				payload = DJson.getObjectMapper().readValue(transport.getPayload(),
						Class.forName(transport.getPayloadClass()));
			} catch (Exception e) {
				throw new DunkNetException(
						"Exception Deserializing Payload class " + transport.getPayloadClass() + " " + e.toString());
			}
			return new DunkNetMessage(payload, transport.getHeaders());
		} else {
			return new DunkNetMessage(transport.getHeaders());
		}

	}
	
	public static final int RESPONSE_SUCCESS = 1; 
	public static final int RESPONSE_ERROR = 2;

	public static final String KEY_PAYLOAD = "payload";
	public static final String KEY_REQUEST_ID = "messageId";
	public static final String KEY_RESPONSE_CODE = "respCode";
	public static final String KEY_RESPONSE_ERROR = "respError";
	public static final String KEY_CHANNEL_ID = "cid";

	public static final int TYPE_PING = 1;
	public static final int TYPE_EVENT = 2;
	public static final int TYPE_SERVICE_REQUEST = 3;
	public static final int TYPE_SERVICE_RESPONSE = 4;
	public static final int TYPE_CHANNEL_REQUEST = 5;
	public static final int TYPE_CHANNEL_RESPONSE = 6;
	public static final int TYPE_CHANNEL_DISPOSE = 7;
	

	private Object payload;
	private Map<String, Object> headers = new ConcurrentHashMap<String, Object>();
	private int type;
	private String channel = null;
	private String messageId = DUUID.randomUUID(5);
	private String senderId; 

	private DunkNetMessage() {

	}

	@Transient
	public boolean isChannelMessage() { 
		if(channel != null) { 
			return true;
		}
		return false;
	}
	
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	private DunkNetMessage(Map<String, Object> headers) {
		this.headers = headers;
	}

	private DunkNetMessage(Object payload, Map<String, Object> headers) {
		this.payload = payload;
		this.headers = headers;
	}

	private DunkNetMessage(int type, Object payload) {
		this.payload = payload;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setHeaders(Map<String, Object> headers) {
		this.headers = headers;
	}

	public boolean hasHeader(String key) {
		if (headers.containsKey(key)) {
			return true;
		}
		return false;
	}

	public Object getHeader(String key) {
		return headers.get(key);
	}

	public void setHeader(String key, Object value) {
		this.headers.put(key, value);
	}

	public Map<String, Object> getHeaders() {
		return headers;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

	public Object getPayload() {
		return payload;
	}
	
	public String getMessageId() { 
		return messageId;
	}
	
	@Transient
	public boolean isServiceRequest() { 
		if(getType() == TYPE_SERVICE_REQUEST) { 
			return true; 
		}
		return false;
	}

	@Transient
	public boolean isServiceResponse() { 
		if(getType() == TYPE_SERVICE_RESPONSE) { 
			return true; 
		}
		return false; 
	}
	
	@Transient
	public String getRequestId() { 
		return getHeader(KEY_REQUEST_ID).toString();
	}
	
	public String getSenderId() { 
		return senderId;
	}
	
	public void setSenderId(String senderId) { 
		this.senderId = senderId;
	}
	
}
