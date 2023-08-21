package com.dunkware.spring.cluster.core;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.DunkNetMessageHandler;

public interface DunkNetController extends DunkNetMessageHandler {

	public void init(DunkNet net);

}
