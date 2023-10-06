package com.dunkware.trade.net.data.server.stream.signals.impl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import com.dunkware.trade.service.data.model.signals.StreamSessionSignalTypeBeans;
import com.dunkware.xstream.model.signal.StreamEntitySignal;

public class StreamSignalIngestorBeanManager extends Thread  {
	
	private BlockingQueue<StreamEntitySignal> signalQueue = new LinkedBlockingDeque<>();
	
	
	public StreamSessionSignalTypeBeans getBeans() { 
		return null;
	}
	
	public void run() { 
		
	}

}
