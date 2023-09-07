package com.dunkware.trade.net.data.server.stream.entitystats.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.admin.KafkaAdminClient;
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
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.trade.net.data.server.stream.entitystats.EntityStreamStats;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;
import com.dunkware.xstream.model.stats.entity.EntityStat;

public class EntityStreamStatsIngestor implements DKafkaByteHandler2 {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("EntityStats");
	
	private BlockingQueue<EntityStat> insertQueue = new LinkedBlockingQueue<EntityStat>(12000000);
	private EntityStreamStats streamStats;
	
	private volatile int insertCountLastSecond = 0;
	private volatile int insertCountMaxSecond = 0;
	private int insertCount = 0;
	private int kafkaConsumeCount = 0;
	
	private Inserter inserter; 
	private InsertSecondCounter secondCounter; 
	
	private List<Inserter> inserts = new ArrayList<Inserter>();
	
	private DKafkaByteConsumer2 kafkaConsumer = null;
	
	private StreamDescriptor descriptor; 
	
	public void init(EntityStreamStats entityStreamStats) { 
		this.streamStats = entityStreamStats;
		this.descriptor = streamStats.getStreamDescriptor();
		//inserter = new Inserter();
		//inserter.start();
		int i = 0;
		while(i < 5) { 
			Inserter inserter = new Inserter();
			inserter.start();
			inserts.add(inserter);
			i++;
		}
		secondCounter = new InsertSecondCounter();
		secondCounter.start();
		
		try {
			String topicName = "stream_" + descriptor.getIdentifier() + "_feed_entitystats";
			String consumerId = descriptor.getIdentifier() + "_entitystats_1";
			String groupId = descriptor.getIdentifier() + "_entitystats_group";
			DKafkaByteConsumer2Spec spec = DKafkaByteConsumer2SpecBuilder.newBuilder(ConsumerType.Auto, OffsetType.Latest).addBroker(descriptor.getKafkaBrokers())
			.addTopic(topicName).setClientAndGroup(consumerId, groupId).setThrottle(5000000).build();
			kafkaConsumer = DKafkaByteConsumer2.newInstance(spec);
			kafkaConsumer.addStreamHandler(this);
			kafkaConsumer.start();
		} catch (Exception e) {
			logger.error(marker, "Exception starting kafka consumer " + e.toString());
		}
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
			logger.error(marker, "Exception Consuming EntityStat record " + e.toString());
		}
	}



	private class InsertSecondCounter extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				int count = insertCount;
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					return;
				}
				insertCountLastSecond = insertCount - count;
				if(insertCountLastSecond > insertCountMaxSecond) {
					insertCountMaxSecond = insertCountLastSecond;
				}
			}
		}
	}
	
	
	
	private class Inserter extends Thread { 
		
		public void run() { 
			Connection cn;
			PreparedStatement st = null;
			int batchCount = 0; 
			
			try {
				cn = streamStats.getConnectionPool().getConnectionFromPool();
				st = cn.prepareStatement(
						"insert into " + streamStats.getStreamIdentifier() + "_entity_stats_session " + "(date,ent,element,stat,value,time) VALUES (?, ?, ?, ?, ?, ?)");
			} catch (Exception e) {
				logger.error(marker,"Exception getting Inserter Connection From Pool " + e.toString());
				
			}
			while(!interrupted()) { 
				try {
					EntityStat stat = insertQueue.poll(5, TimeUnit.SECONDS);
					if(stat == null && batchCount > 0) { 
						try {
							st.executeBatch();
							insertCount = insertCount + batchCount;
							batchCount = 0;
						} catch (Exception e) {
							logger.error("EntityStats", "Exception bulk insert on batch " + e.toString());
						}
						continue;
					}
					if(stat == null) { 
						continue;
					}
					st.setDate(1, java.sql.Date.valueOf(stat.getDate()));
					st.setInt(2,stat.getEntity());
					st.setInt(3, stat.getElement());
					st.setInt(4, stat.getStat());
					st.setDouble(5,stat.getValue().doubleValue());
					st.setTime(6, null);
					//if(stat.getTime() != null)
					//st.setTime(6, java.sql.Time.valueOf(stat.getTime()));
					try {
						st.addBatch();	
						batchCount++;
					} catch (Exception e) {
						logger.error(marker, "Exception adding to batch in ingestor writer thread " + e.toString());
						continue;
					}
					if(batchCount == 10000) { 
						try {
							st.executeBatch();
							insertCount = insertCount + batchCount;
						} catch (Exception e) {
							logger.error(marker, "Exception inserting batch count = 3000K execute exception " + e.toString());
						}
						batchCount = 0; 
					}
					
				} catch (Exception e) {
					logger.error(marker, "Exception outer exception in writer thread  " + e.toString());
				}
			}
			
		}
	}
}
