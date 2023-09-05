package com.dunkware.xstream.api;

import com.dunkware.xstream.model.signal.StreamEntitySignal;

public interface XStreamSignalListener {
	
	public void onSignal(StreamEntitySignal signal, XStreamEntity entity);

}
