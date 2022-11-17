package com.dunkware.xstream.core.stats;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamRowSignal;
import com.dunkware.xstream.api.XStreamSignalListener;

public class SignalStatsBuilder implements XStreamSignalListener  {
	
	private Map<Integer,AtomicInteger> counts = new ConcurrentHashMap<Integer,AtomicInteger>();
	private XStream stream; 
	private SessionStatsService service; 
	
	
	public void init(XStream stream, SessionStatsService service) { 
		this.stream = stream;
		this.service = service; 
		this.stream.addSignalListener(this);
	}
	
	public void dispose() {
		stream.removeSignalListener(this);
	}
	
	@Override
	public void onSignal(XStreamRowSignal signal) {
		if(counts.get(signal.getSignalType().getId())== null) { 
			counts.put(signal.getSignalType().getId(), new AtomicInteger(1));
		} else { 
			counts.get(signal.getSignalType().getId()).incrementAndGet();
		}
		
	}
	
	public Map<Integer,AtomicInteger> getCounts() { 
		return counts;
	}

	
	
	
	
	
}
