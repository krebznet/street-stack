package com.dunkware.cluster.proto.message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.common.util.json.DJson;

public class Message {
	
	public static Message newInstance() { 
		return new Message();
	}
	
	public static Message newInstance(Object payload) { 
		return new Message(payload);
	}
	
	public static Message newInstance(MessageTransport transport) throws MessageException { 
		Object payload = null;
		if(transport.getPayload() != null) { 
			try {
				payload = DJson.getObjectMapper().readValue(transport.getPayload(), Class.forName(transport.getPayloadClass()));				
			} catch (Exception e) {
				throw new MessageException("Exception Deserializing Payload class " + transport.getPayloadClass() + " " + e.toString());
			}
			return new Message(payload,transport.getHeaders());
		} else { 
			return new Message(transport.getHeaders());
		}
		
	}
	
	public static final String HEADER_KEY_PAYLOAD_CLASS = "payloadClass";
	public static final String HEADER_KEY_MESSAGE_TYPE = "messageType"; 
	public static final String HEADER_KEY_MESSAGE_ID = "messageId";
	public static final String HEADER_KEY_MESSAGE_RESPONSE_ERROR = "responseError";
	public static final String HEADER_KEY_MESSAGE_REQUEST_ID = "requestId";
	
	public static final String HEADER_MESSAGE_TYPE_MESSAGE = "message";
	public static final String HEADER_MESSAGE_TYPE_REQUEST = "request";
	public static final String HEADER_REQUEST_TYPE_RESPONSE = "response";
	
	private Object payload; 
	private Map<String,Object> headers = new ConcurrentHashMap<String,Object>();
	
	
	private Message() { 
		
	}
	
	private Message(Map<String,Object> headers) { 
		this.headers = headers; 
	}
	
	private Message(Object payload, Map<String,Object> headers) { 
		this.payload = payload; 
		this.headers = headers;
	}
	
	private Message(Object payload) { 
		this.payload = payload; 
	}
	
	public boolean hasHeader(String key) { 
		if(headers.containsKey(key)) { 
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
	
	public Map<String,Object> getHeaders() { 
		return headers;
	}
	
	public void setPayload(Object payload) { 
		this.payload = payload;
	}
	
	public Object getPayload() { 
		return payload; 
	}
	
}
