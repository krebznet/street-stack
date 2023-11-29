package com.dunkware.trade.service.stream.worker.session.stats;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.trade.service.stream.worker.session.StreamWorker;
import com.dunkware.trade.service.stream.worker.session.StreamWorkerExtension;
import com.dunkware.trade.service.stream.worker.session.anot.AStreamWorkerExtension;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.model.stats.entity.EntityStat;

@AStreamWorkerExtension()
public class StreamWorkerStatsExtension implements StreamWorkerExtension {
	
	private StreamWorker worker; 
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker  = MarkerFactory.getMarker("StreamWorkerEntityStats");
	
	
	private XStream stream; 
	
	private List<EntityStat> stat = new ArrayList<EntityStat>();
	@Override
	public void init(StreamWorker worker) {
		this.worker = worker; 
		
	}

	@Override
	public void start() {
		stream = worker.getStream();
		
	}

	@Override
	public void stop() {
		List<EntityStat> stats = new ArrayList<EntityStat>();
		StreamEntityStatFactory statFactory = new StreamEntityStatFactory();
		String topicName = worker.getStartReq().getStreamProperties().get("stats_topic");
		try {
			DStopWatch watch = DStopWatch.create();
			watch.start();
			stats = statFactory.buildEntityStats(stream);			
			watch.stop();
			logger.info(marker, "Generated Entity Stats in " + watch.getCompletedSeconds());
			StreamWorkerEntityStatsPublisher publisher = new StreamWorkerEntityStatsPublisher(stats, topicName, worker.getDunkNet());
			publisher.run();
		} catch (Exception e) {
			logger.error(marker, "Exception building entity stats " + e.toString());
		}
		
	}
	
	
	// MYSQLEntityStatWriter
	


	

	
}
