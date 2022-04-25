package com.dunkware.net.cluster.server.core;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.net.cluster.json.node.ClusterNodeStats;
import com.dunkware.net.cluster.server.config.ClusterConfig;

public class ClusterNodeService implements DKafkaByteHandler2  {
	
	@Autowired
	private ClusterConfig config; 
	
	@Autowired
	private ApplicationContext ac;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private Map<String,ClusterNode> nodes = new ConcurrentHashMap<String,ClusterNode>();
	
	private BlockingQueue<ClusterNodeStats> statsQueue = new LinkedBlockingQueue<ClusterNodeStats>();
	
	private DKafkaByteConsumer2 statConsumer;
	
	private StatProcessor statsProcessor;
	
	public void start(ClusterService cluster) throws Exception { 
		DKafkaByteConsumer2Spec spec = null;
		try {
			spec = DKafkaByteConsumer2SpecBuilder.newBuilder(ConsumerType.Auto, OffsetType.Latest)
			.addBroker(config.getKafkaBrokers()).addTopic("cluster_core_node_stats").
			setClientAndGroup("ClusterPingConsumer" + DunkTime.formatHHMMSS(LocalDateTime.now()),
					DunkTime.formatHHMMSS(LocalDateTime.now())).build();
		 statConsumer = DKafkaByteConsumer2.newInstance(spec);
		 statConsumer.start();
		 statConsumer.addStreamHandler(this);
		} catch (Exception e) {
			logger.error("Exception Starting Cluster Node Manager Kafka Cluster " + e.toString());
			throw e;
		}
		
		statsProcessor = new StatProcessor();
		statsProcessor.start();
		
	}
	
	public Collection<ClusterNode> getNodes() { 
		return nodes.values();
	}
	
	

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		// okay deserialize from json bytes
		ClusterNodeStats stats = null;
		try {
			
			 stats = DJson.getObjectMapper().readValue(record.value(), ClusterNodeStats.class);	
			 if(logger.isTraceEnabled()) { 
					logger.trace("Consumed ClusterNodeStats From " + stats.getId()	);
				}
			 statsQueue.add(stats);
		} catch (Exception e) {
			logger.error("Exception deserializing ClusterNodeStats " + e.toString());
		}
		
	}
	
	
	private class StatProcessor extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				ClusterNodeStats stats = null;
				try {
					stats = statsQueue.take();
				} catch (Exception e) {
					logger.error("Exception taking stats from queue " + e.toString());
					continue;
				}
				ClusterNode node = nodes.get(stats.getId());
				if(node == null) { 
					node = new ClusterNode();
					ac.getAutowireCapableBeanFactory().autowireBean(node);
					node.start(stats);
					nodes.put(stats.getId(), node);
				} else {
					node.updateNodeState(stats);
				}
				
			}
			
		}
		
		
		
	}
	
	public ClusterNode getNode(String id) throws Exception {
		ClusterNode node = nodes.get(id);
		if(node == null) { 
			int count = 0;
			while(node == null) { 
				try {
					Thread.sleep(250);
					node = nodes.get(id);
					if(node != null) { 
						break;
					}
					count++;
					if(count > 35) { 
						throw new Exception("Node " + id + " not found after waiting 3 seconds");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			throw new Exception("Node " + id + " not found");
		}
		return node;
		
	}
	
	private class NoeStatsStreamPublisher extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				
			}
		}
		
	}
	
	
}
