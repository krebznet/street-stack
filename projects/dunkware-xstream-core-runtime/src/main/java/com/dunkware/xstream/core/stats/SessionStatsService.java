package com.dunkware.xstream.core.stats;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.json.DJson;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamException;
import com.dunkware.xstream.api.XStreamListener;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.api.XStreamService;
import com.dunkware.xstream.core.annotations.AXStreamService;
import com.dunkware.xstream.model.stats.SignalStats;
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
		signalStatsbuilder.init(stream, this);
	
		
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
		signalStatsbuilder.dispose();
		for (EntityStatsBuilder builder : entityStatsBuilders.values()) {
			builder.dispose();
		}
		try {
			System.out.println(DJson.serializePretty(getStats()));	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void rowInsert(XStreamRow row) {
		EntityStatsBuilder builder = new EntityStatsBuilder();
		builder.init(row, this);
		this.entityStatsBuilders.put(row.getId(), builder);
	}
	
	public StreamStats getStats() { 
		StreamStats stats = new StreamStats();
		stats.setDate(stream.getClock().getDate());
		stats.setStreamIdentifier(stream.getInput().getIdentifier());
		for (EntityStatsBuilder builder : entityStatsBuilders.values()) {
			stats.getEntityStats().add(builder.getStats());
		}
		Collection<SignalStats> sigStats = signalStatsbuilder.getStats();
		for (SignalStats sd : sigStats) {
			stats.getSignalStats().add(sd);
		}
		return stats;
		
	}
	
	
	

}
