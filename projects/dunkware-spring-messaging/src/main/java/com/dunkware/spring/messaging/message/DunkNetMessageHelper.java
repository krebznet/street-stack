package com.dunkware.spring.messaging.message;

import com.dunkware.common.util.json.DJson;
import com.dunkware.spring.messaging.DunkNetException;

public class DunkNetMessageHelper {

	
	public static DunkNetMessageTransport toTransport(DunkNetMessage message, String senderId) throws DunkNetException { 
		DunkNetMessageTransport transport = new DunkNetMessageTransport();
		transport.setHeaders(message.getHeaders());
		if(message.getPayload() != null) { 
			String payloadString = null; 
			try {
				payloadString = DJson.serialize(message.getPayload());
			} catch (Exception e) {
				throw new DunkNetException("Exception serializing payload " + e.toString());
			}
			transport.setPayload(payloadString);
			transport.setPayloadClass(message.getPayload().getClass().getName());;		
			transport.setSenderId(senderId);
		}

		return transport; 
	}
	
	
}
