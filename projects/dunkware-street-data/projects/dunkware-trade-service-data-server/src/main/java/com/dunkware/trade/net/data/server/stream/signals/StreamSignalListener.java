package com.dunkware.trade.net.data.server.stream.signals;

import com.dunkware.xstream.model.signal.StreamEntitySignal;

public interface StreamSignalListener {
	
	public void onSignal(StreamEntitySignal signal);

}
