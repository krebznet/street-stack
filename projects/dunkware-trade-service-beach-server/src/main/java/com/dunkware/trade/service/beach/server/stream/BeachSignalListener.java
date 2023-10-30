package com.dunkware.trade.service.beach.server.stream;

import com.dunkware.xstream.model.signal.StreamEntitySignal;

public interface BeachSignalListener {
	
	void onStreamSignal(StreamEntitySignal signal);

}
