package com.dunkware.xstream.api;

import java.util.concurrent.atomic.AtomicInteger;

import com.dunkware.xstream.core.annotations.AXStreamService;

@AXStreamService(profiles = "*")
public class XStreamSignalService implements XStreamService, XStreamSignalListener  {
	
	private XStream stream; 
	private AtomicInteger sigCount = new AtomicInteger(0);

	@Override
	public void init(XStream stream) throws XStreamException {
		this.stream = stream; 
		stream.addSignalListener(this);
		
	}

	@Override
	public void preStart() throws XStreamException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() throws XStreamException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preDispose() {
		stream.removeSignalListener(this);
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSignal(XStreamRowSignal signal) {
		sigCount.incrementAndGet();
		
	}
	
	
	public int getSignalCount() { 
		return sigCount.get();
	}
	
	

}
