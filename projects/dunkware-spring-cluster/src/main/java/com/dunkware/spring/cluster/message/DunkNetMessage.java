package com.dunkware.spring.cluster.message;

import java.beans.Transient;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.spring.cluster.protocol.descriptors.DunkNetDescriptors;

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
		
		public Builder channelClose(String channelId) { 
			m.setType(TYPE_CHANNEL_CLOSE);
			m.setChannel(channelId);
			return this;
		}
		
		

		public Builder channelRequest(String channelId, Object payload) { 
			m.setType(TYPE_CHANNEL_REQUEST);
			m.setPayload(payload);
			m.setChannel(channelId);
			return this;
		}
		
		
		public Builder channelRequest(Object payload) { 
			m.setType(TYPE_CHANNEL_REQUEST);
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
			m.setHeader(KEY_REQUEST_ID, requestId);
			m.setHeader(KEY_RESPONSE_CODE, RESPONSE_SUCCESS);
			m.setPayload(payload);
			return this;
		}
		
		
		
		public Builder serviceResponseError(String requestId, String exception) {  
			m.setType(TYPE_SERVICE_RESPONSE);
			m.setHeader(KEY_RESPONSE_CODE, RESPONSE_ERROR);
			m.setHeader(KEY_RESPONSE_ERROR, exception);
			return this;
		}
		
		public Builder channelResponseWithParent(String parentChannelId, String channelId, String requestId, DunkNetDescriptors descriptors) { 
			m.setType(TYPE_CHANNEL_RESPONSE);
			m.setHeader(KEY_REQUEST_ID, requestId);
			m.setPayload(descriptors);
			m.setChannel(channelId);;
			m.setParentChannel(parentChannelId);
			return this;
		}
		
		public Builder channelResponse(String channelId, String requestId, DunkNetDescriptors descriptors) { 
			m.setType(TYPE_CHANNEL_RESPONSE);
			m.setHeader(KEY_RESPONSE_CODE, RESPONSE_SUCCESS);
			m.setHeader(KEY_REQUEST_ID, requestId);
			m.setPayload(descriptors);
			m.setChannel(channelId);;
			return this;
		}
		
		public Builder channelInit(String channelId) { 
			m.setType(TYPE_CHANNEL_CLIENT_INIT);
			m.setChannel(channelId);
			return this;
		}
		
		
		public Builder channelException(String parentChannelId, String channelId, String requestId, String error) { 
			m.setType(TYPE_CHANNEL_RESPONSE);
			m.setHeader(KEY_REQUEST_ID, requestId);
			m.setChannel(channelId);
			m.setParentChannel(parentChannelId);
			m.setHeader(KEY_RESPONSE_ERROR, error);
			return this;
		}
		
		public Builder channelClientInit(String channelId, DunkNetDescriptors descriptors) { 
			m.setType(TYPE_CHANNEL_CLIENT_INIT);
			m.setChannel(channelId);
			m.setPayload(descriptors);
			return this;
		}
		
		public Builder channelClientStartError(String channelId, String error) { 
			m.setType(TYPE_CHANNEL_CLIENT_STARRT_ERROR);
			m.setHeader(KEY_RESPONSE_ERROR, error);
			m.setChannel(channelId);
			return this;
		}
		
		
		public Builder channelClientInitError(String channelId, String error) { 
			m.setType(TYPE_CHANNEL_CLIENT_INIT_ERROR);
			m.setChannel(channelId);
			m.setHeader(KEY_ERROR, error);
			return this;	
		}
		
		public Builder channelServerStart(String channelId) { 
			m.setType(TYPE_CHANNEL_SERVER_START);
			m.setChannel(channelId);
			return this;	
		}
		
		public Builder channelServerStartError(String channelId, String error) { 
			m.setType(TYPE_CHANNEL_SERVER_START_ERROR);
			m.setHeader(KEY_ERROR, error);
			m.setChannel(channelId);
			return this;	
		}
		
		public Builder channelException(String channelId, String requestId, String error) { 
			m.setType(TYPE_CHANNEL_RESPONSE);
			m.setHeader(KEY_REQUEST_ID, requestId);
			m.setChannel(channelId);
			m.setHeader(KEY_RESPONSE_CODE, RESPONSE_ERROR);
			m.setHeader(KEY_RESPONSE_ERROR, error);
			return this;
		}

		public Builder channelServiceRequest(String channelId, Object payload ) {
			m.setType(TYPE_SERVICE_REQUEST);
			m.setChannel(channelId);
			m.setPayload(payload);
			return this;
		}
		
		public Builder channelServiceResponseError(String channelId, String requestId, String error) { 
			m.setType(TYPE_SERVICE_RESPONSE);
			m.setHeader(KEY_RESPONSE_CODE, RESPONSE_ERROR);
			m.setHeader(KEY_ERROR,error);
			m.setHeader(KEY_REQUEST_ID, requestId);
			m.setChannel(channelId);
			return this;
		}
		
		public Builder channelServiceResponse(String channelId, String requestId, Object payload) { 
			m.setType(TYPE_SERVICE_RESPONSE);
			m.setHeader(KEY_RESPONSE_CODE, RESPONSE_SUCCESS);
			m.setHeader(KEY_REQUEST_ID, requestId);
			m.setChannel(channelId);
			m.setPayload(payload);
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
		}
			DunkNetMessage message = new DunkNetMessage();
			message.setChannel(transport.getChannel());
			message.setHeaders(transport.getHeaders());
			message.setType(transport.getType());
			message.setSenderId(transport.getSenderId());
			message.setPayload(payload);;
			message.setMessageId(transport.getMessageId());
			message.setParentChannel(transport.getParentChannel());
			return message;
			
		

	}
	
	public static final int RESPONSE_SUCCESS = 1; 
	public static final int RESPONSE_ERROR = 2;

	public static final String KEY_PAYLOAD = "payload";
	public static final String KEY_REQUEST_ID = "messageId";
	public static final String KEY_RESPONSE_CODE = "respCode";
	public static final String KEY_RESPONSE_ERROR = "respError";
	
    public static final String KEY_ERROR = "error";
	public static final int TYPE_PING = 1;
	public static final int TYPE_EVENT = 2;
	public static final int TYPE_SERVICE_REQUEST = 3;
	public static final int TYPE_SERVICE_RESPONSE = 4;
	public static final int TYPE_CHANNEL_REQUEST = 5;
	public static final int TYPE_CHANNEL_RESPONSE = 6;
	public static final int TYPE_CHANNEL_CLOSE = 7;
	public static final int TYPE_CHANNEL_CLIENT_INIT = 8;
	public static final int TYPE_CHANNEL_CLIENT_INIT_ERROR = 13;
	public static final int TYPE_CHANNEL_CLIENT_STARRT_ERROR = 14;
	public static final int TYPE_CHANNEL_SERVER_START = 9;
	public static final int TYPE_CHANNEL_SERVER_START_ERROR = 10;
	
	
	

	private Object payload;
	private Map<String, Object> headers = new ConcurrentHashMap<String, Object>();
	private int type;
	private String channel = null;
	private String messageId = DUUID.randomUUID(5);
	private String senderId; 
	private String parentChannel = null;

	private DunkNetMessage() {

	}

	public void setMessageId(String messageId) { 
		this.messageId = messageId;
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
	
	@Transient
	public boolean hasParentChannel() { 
		if(parentChannel == null) { 
			return false; 
		}
		return true;
	}

	public String getParentChannel() {
		return parentChannel;
	}

	public void setParentChannel(String parentChannel) {
		this.parentChannel = parentChannel;
	}
	
	
}
