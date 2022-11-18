package com.dunkware.xstream.core.stats;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamRowSignal;
import com.dunkware.xstream.api.XStreamSignalListener;
import com.dunkware.xstream.model.stats.SignalStats;

public class SignalStatsBuilder implements XStreamSignalListener  {
	
	private Map<String,SignalStats> map = new ConcurrentHashMap<String, SignalStats>();
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
		SignalStats stats = map.get(signal.getSignalType().getName());
		if(stats == null) { 
			stats = new SignalStats();
			stats.setDate(stream.getClock().getDate());
			stats.setSidId(signal.getSignalType().getId());
			stats.setSigIdent(signal.getSignalType().getName());
			stats.setCount(1);
			map.put(signal.getSignalType().getName(), stats);
		} else { 
			stats.setCount(stats.getCount() + 1);
			map.put(signal.getSignalType().getName(), stats);
		}
		
	}
	
	public Collection<SignalStats> getStats() { 
		return map.values();
	}

	
	
	
	
	
}
