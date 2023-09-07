package com.dunkware.trade.service.stream.worker.session.stats;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.xstream.model.stats.entity.EntityStat;

public class StreamWorkerEntityStatsDumper extends Thread {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamStatsPublisher");
	
	private List<EntityStat> stats;
	private String streamIdent; 
	private String brokers; 
	
	public StreamWorkerEntityStatsDumper(List<EntityStat> stats, String streamIdent, String brokers) { 
		this.stats = stats;
		this.streamIdent = streamIdent;
		this.brokers = brokers;
	}
	
	public void run() { 
		try {
			DStopWatch timer = DStopWatch.create();
			timer.start();
			logger.info(marker, "Starting Stream Stats Publisher With " + stats.size() + " Entity Stats");
			String topicName = "stream_" + streamIdent + "_feed_entitystats";
			DKafkaByteProducer producer = DKafkaByteProducer.newInstance(brokers,topicName,streamIdent + 1);
			for (EntityStat entityStat : stats) {
				producer.sendBytes(DJson.serialize(entityStat).getBytes());
			}
			timer.stop();
			logger.info(marker, "Finished Stream Stats Publisher With " + stats.size() + " Entity Stats in {} seconds", timer.getCompletedSeconds());
			producer.dispose();
		} catch (Exception e) {
			logger.error(marker, "Execption Publishing Stream Entity Stats " + e.toString(),e);
		}
	}
}
