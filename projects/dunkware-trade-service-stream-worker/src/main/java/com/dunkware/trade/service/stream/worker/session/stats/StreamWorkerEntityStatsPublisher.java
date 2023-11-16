package com.dunkware.trade.service.stream.worker.session.stats;

import java.util.List;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.xstream.model.stats.entity.EntityStat;

public class StreamWorkerEntityStatsPublisher   {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamStatsPublisher");
	Marker stop = MarkerFactory.getMarker("SessionStop");
	private List<EntityStat> stats;
	private DunkNet dunkNet;
	private String topicName = null;
	
	public StreamWorkerEntityStatsPublisher(List<EntityStat> stats, String topicName, DunkNet dunkNet) { 
		this.stats = stats;
		this.topicName = topicName;
		this.dunkNet = dunkNet;
	}
	
	public void run() { 
		try {
			DStopWatch timer = DStopWatch.create();
			timer.start();
			logger.info(stop,"publishing  stat messages on worker");
			logger.info(marker, "Starting Stream Stats Publisher With " + stats.size() + " Entity Stats");
			
			for (EntityStat entityStat : stats) {
				byte[] serialized = null;
				try {
					serialized = DJson.serialize(entityStat).getBytes();
				} catch (Exception e) {
					logger.error("Fucked Up could not serialize session entity stats " + e.toString());
					continue;
				}
				try {
					ProducerRecord<Integer, byte[]> record = new ProducerRecord<Integer, byte[]>(topicName, entityStat.getEntity(),serialized);
					dunkNet.getKafkaProducer().send(record);	
				} catch (Exception e) {
					logger.error("Fucked up sending stat kafka record " + e.toString());
				}
				
			}
			timer.stop();
			logger.info(stop,"sent stat messages " + stats.size());
		} catch (Exception e) {
			logger.error(marker, "Execption Publishing Stream Entity Stats " + e.toString(),e);
		}
		
		
		
	
	}
}
