package com.dunkware.spring.messaging.core;

import com.dunkware.spring.messaging.DunkNet;
import com.dunkware.spring.messaging.DunkNetMessageHandler;

public interface DunkNetController extends DunkNetMessageHandler {

	public void init(DunkNet net);

}
