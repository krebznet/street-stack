package com.dunkware.xstream.core.stats;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamException;
import com.dunkware.xstream.api.XStreamListener;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.api.XStreamService;
import com.dunkware.xstream.core.annotations.AXStreamService;
import com.dunkware.xstream.model.stats.StreamStats;

@AXStreamService(profiles = "XStreamStatsService")
public class SessionStatsService implements XStreamService, XStreamListener {

	private XStream stream;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Map<String,EntityStatsBuilder> entityStatsBuilders = new ConcurrentHashMap<String,EntityStatsBuilder>();
	private SignalStatsBuilder signalStatsbuilder = new SignalStatsBuilder();

	@Override
	public void init(XStream stream) throws XStreamException {
		this.stream = stream; 
		this.stream.addStreamListener(this);
	
		
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
		
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rowInsert(XStreamRow row) {
		EntityStatsBuilder builder = new EntityStatsBuilder();
		builder.init(row, this);
		this.entityStatsBuilders.put(row.getId(), builder);
	}
	
	public StreamStats getStats() { 
		return null;
	}
	
	
	

}
