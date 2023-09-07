package com.dunkware.trade.service.stream.worker.session.stats;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.trade.service.stream.worker.session.StreamWorker;
import com.dunkware.trade.service.stream.worker.session.StreamWorkerExtension;
import com.dunkware.trade.service.stream.worker.session.anot.AStreamWorkerExtension;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamListener;
import com.dunkware.xstream.model.stats.entity.EntityStat;
import com.dunkware.xstream.model.stats.entity.EntityStats;

@AStreamWorkerExtension()
public class StreamWorkerStatsExtension implements StreamWorkerExtension, XStreamListener {
	
	private StreamWorker worker; 
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker  = MarkerFactory.getMarker("StreamWorkerEntityStats");
	
	private List<StreamEntityStatsBuilder> statsBuilders = new ArrayList<StreamEntityStatsBuilder>();
	private XStream stream; 
	
	private List<EntityStat> stat = new ArrayList<EntityStat>();
	@Override
	public void init(StreamWorker worker) {
		this.worker = worker; 
	}

	@Override
	public void start() {
		stream = worker.getStream();
		for (XStreamEntity entity : stream.getRows()) {
			StreamEntityStatsBuilder builder = new StreamEntityStatsBuilder();
			builder.start(entity,worker.getStream().getSignals());
			statsBuilders.add(builder);
		}
		stream.addStreamListener(this);
	}

	@Override
	public void stop() {
		List<EntityStat> stats = new ArrayList<EntityStat>();
		for (StreamEntityStatsBuilder builder : statsBuilders) {
			builder.collectStats(stats);
		}
		logger.info(marker, "Collected " + stats.size() + " sats");
		StreamWorkerEntityStatsDumper publisher = new StreamWorkerEntityStatsDumper(stats, stream.getInput().getIdentifier(), worker.getDunkNet().getConfig().getServerBrokers());
		publisher.start();
		
		
	}
	
	

	@Override
	public void rowInsert(XStreamEntity row) {
		StreamEntityStatsBuilder builder = new StreamEntityStatsBuilder();
		builder.start(row,worker.getStream().getSignals());
		statsBuilders.add(builder);
	}
	
	

	
}
