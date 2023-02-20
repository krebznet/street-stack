package com.dunkware.cluster.proto.message;

import com.dunkware.common.util.json.DJson;

public class MessageHelper {

	
	public static MessageTransport toTransport(Message message) throws Exception { 
		MessageTransport transport = new MessageTransport();
		transport.setHeaders(message.getHeaders());
		if(message.getPayload() != null) { 
			String payloadString = null; 
			try {
				payloadString = DJson.serialize(message.getPayload());
			} catch (Exception e) {
				throw new Exception("Exception serializing payload " + e.toString());
			}
			transport.setPayload(payloadString);
			transport.setPayloadClass(message.getPayload().getClass().getName());;			
		}

		return transport; 
	}
	
	
}
