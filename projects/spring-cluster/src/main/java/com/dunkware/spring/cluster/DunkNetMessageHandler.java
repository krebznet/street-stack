package com.dunkware.spring.cluster;

import com.dunkware.spring.cluster.message.DunkNetMessage;

public interface DunkNetMessageHandler {

	public boolean handleMessage(DunkNetMessage message) throws DunkNetException;
}
