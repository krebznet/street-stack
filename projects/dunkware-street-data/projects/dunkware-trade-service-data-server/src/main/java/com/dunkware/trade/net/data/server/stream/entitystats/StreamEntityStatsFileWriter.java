package com.dunkware.trade.net.data.server.stream.entitystats;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.net.data.server.stream.entitystats.file.EntityStatFileWriter;
import com.dunkware.trade.net.data.server.stream.entitystats.repository.EntityStatsEnt;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;
import com.dunkware.xstream.model.stats.entity.EntityStat;

public class StreamEntityStatsFileWriter implements DKafkaByteHandler2 {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamEntityStatsFileWriter");

	private BlockingQueue<EntityStat> insertQueue = new LinkedBlockingQueue<EntityStat>(12000000);
	private StreamEntityStats streamStats;

	private volatile int insertCountLastSecond = 0;
	private volatile int insertCountMaxSecond = 0;
	private volatile int insertCount = 0;
	private int kafkaConsumeCount = 0;

	private Inserter inserter;
	
	private List<Inserter> inserts = new ArrayList<Inserter>();

	private DKafkaByteConsumer2 kafkaConsumer = null;

	private StreamDescriptor descriptor;

	private EntityStatsEnt entity;
	private StreamEntityStatsImpl statsImpl;

	private EntityStatFileWriter fileWriter;
	
	private StreamEntityStatsFileWriterListener listener; 
	
	private String error; 

	public void init(EntityStatsEnt entity, StreamEntityStatsImpl statsImpl, StreamEntityStatsFileWriterListener listener)   {
		logger.info(marker, "Starting Stream Entity Stats Writer"	);
		this.entity = entity;
		this.statsImpl = statsImpl;
		this.listener = listener;
		this.streamStats = statsImpl;
		inserter = new Inserter();
		inserter.start();
		this.descriptor = statsImpl.getStreamDescriptor();
		try {
			fileWriter = EntityStatFileWriter.newInstance(entity.getSessionFileDirectory(), entity.getSessionFile(),
					true);
		} catch (Exception e) {
			logger.error(marker, "Exception creating file writer " + e.toString(),e);
			error = "Exception creating file writer " + e.toString();
			listener.onException(this);
		}

		try {
			String topicName = "stream_" + descriptor.getIdentifier() + "_feed_entitystats";
			String consumerId = descriptor.getIdentifier() + "_entitystats_1";
			String groupId = descriptor.getIdentifier() + "_entitystats_group";
			DKafkaByteConsumer2Spec spec = DKafkaByteConsumer2SpecBuilder
					.newBuilder(ConsumerType.Auto, OffsetType.Latest).addBroker(descriptor.getKafkaBrokers())
					.addTopic(topicName).setClientAndGroup(consumerId, groupId).setThrottle(5000000).build();
			kafkaConsumer = DKafkaByteConsumer2.newInstance(spec);
			kafkaConsumer.addStreamHandler(this);
			kafkaConsumer.start();
		} catch (Exception e) {
			logger.error(marker, "Excepton connecing to kafka");;
			this.error = "Exception connecting to kafka " + e.toString();
			logger.error(marker, "Exception starting kafka consumer " + e.toString());
			listener.onException(this);
		}
	}
	
	public String getError() { 
		return error; 
	}
	
	public EntityStatsEnt getRecord() { 
		return entity;
	}

	public void insert(EntityStat stat) throws Exception {
		insertQueue.add(stat);
	}

	public int getQueueSize() {
		return insertQueue.size();
	}

	public int getKafkaConsumeCount() {
		return kafkaConsumeCount;
	}

	public int getInsertCountMaxSecond() {
		return insertCountMaxSecond;
	}

	public int getInsertCountLastSecond() {
		return insertCountLastSecond;
	}

	public int getInsertCount() {
		return insertCount;
	}

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		try {
			
			EntityStat stat = DJson.getObjectMapper().readValue(record.value(), EntityStat.class);
			kafkaConsumeCount++;
			this.insertQueue.add(stat);
		} catch (Exception e) {
			logger.error(marker, "Exception pulling kafka record " + e.toString());;
			kafkaConsumer.dispose();
			error = "Exception pulling kafka record " + e.toString();
			listener.onException(this);
			logger.error(marker, "Exception Consuming EntityStat record " + e.toString());
		}
	}
	
	
	public void complete() { 
		kafkaConsumer.dispose();
		listener.onComplete(this);
		
	}

	/**
	 * So one thread should be enough. 
	 * @author duncankrebs
	 *
	 */
	private class Inserter extends Thread {

		public void run() {

			int batchCount = 0;
			int emptyPoolCount = 0; 
			while (!interrupted()) {
				try {
					EntityStat stat = insertQueue.poll(5, TimeUnit.SECONDS);
					if (stat == null) {
						emptyPoolCount++;
						if(emptyPoolCount > 3) { 
							fileWriter.close();
							try {
								complete();	
							} catch (Exception e) {
								logger.error(" on complete exception " + e.toString(),e);
							}
							
							return;
						}
						continue;
					} else { 
						try {
							fileWriter.writeStat(stat);;
							insertCount++;	
						} catch (Exception e) {
							logger.error(marker, "Exception writing stat " + e.toString() + " stat to string " + stat.toString() );
						}
						
					}
				}
				catch(InterruptedException e1) { 
					return;
				}
				
				
				catch (Exception e) {
					logger.error(marker, "Write error " + e.toString());;
					error = "outer write error  " + e.toString()	;
					listener.onException(StreamEntityStatsFileWriter.this);
					logger.error(marker, "Exception outer exception in writer thread  " + e.toString());
				}
			}

		}
	}
}
