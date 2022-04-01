package com.dunkware.xstream.core.services;

import java.util.concurrent.atomic.AtomicInteger;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamException;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.api.XStreamRowListener;
import com.dunkware.xstream.api.XStreamRowSignal;
import com.dunkware.xstream.api.XStreamService;
import com.dunkware.xstream.core.annotations.AXStreamService;
import com.dunkware.xstream.model.signal.XStreamSignalSpec;

@AXStreamService(profiles = "Runtime")
public class XStreamSignalService implements XStreamService, XStreamRowListener {
	
	private XStream stream; 
	private AtomicInteger signalCount = new AtomicInteger(0);

	@Override
	public void init(XStream stream) throws XStreamException {
		this.stream = stream;
		
	}

	@Override
	public void preStart() throws XStreamException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() throws XStreamException {
		stream.addRowListener(this);
	}

	@Override
	public void preDispose() {
		stream.removeRowListener(this);
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rowSignal(XStreamRow row, XStreamRowSignal signal) {
		signalCount.incrementAndGet();
	}
	
	public int getSignalCount() { 
		return signalCount.get();
	}
	
	

}
