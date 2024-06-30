package com.dunkware.spring.cluster.message;

import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.utils.core.json.DunkJson;

public class DunkNetMessageHelper {

	
	public static DunkNetMessageTransport toTransport(DunkNetMessage message, String senderId) throws DunkNetException { 
		DunkNetMessageTransport transport = new DunkNetMessageTransport();
		transport.setHeaders(message.getHeaders());
		if(message.getPayload() != null) { 
			String payloadString = null; 
			try {
				payloadString = DunkJson.serialize(message.getPayload());
			} catch (Exception e) {
				throw new DunkNetException("Exception serializing payload " + e.toString());
			}
			transport.setPayload(payloadString);
			transport.setPayloadClass(message.getPayload().getClass().getName());;		
			
		}

		transport.setSenderId(senderId);
		transport.setMessageId(message.getMessageId());
		transport.setChannel(message.getChannel());
		transport.setType(message.getType());
		transport.setParentChannel(message.getParentChannel());
		return transport; 
	}
	
	
}
