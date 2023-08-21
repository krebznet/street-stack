package com.dunkware.spring.messaging;

import com.dunkware.spring.messaging.message.DunkNetMessage;

public interface DunkNetMessageHandler {

	public boolean handleMessage(DunkNetMessage message) throws DunkNetException;
}
