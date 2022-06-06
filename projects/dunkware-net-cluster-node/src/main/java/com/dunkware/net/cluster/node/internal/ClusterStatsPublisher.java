package com.dunkware.net.cluster.node.internal;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.util.json.DJson;
import com.dunkware.net.cluster.node.Cluster;

@Service
public class ClusterStatsPublisher {

	@Autowired
	private Cluster cluster; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ClusterConfig config;
	
	private DKafkaByteProducer pingProducer; 
	
	private PingSender pingSender;
	
	@PostConstruct
	public void start() throws Exception { 
		try {
			pingProducer = DKafkaByteProducer.newInstance(config.getServerBrokers(), "cluster_core_node_stats", cluster.getNodeId());
		} catch (Exception e) {
			logger.error("Exception starting cluster node ping producer " + e.toString());
			throw e;
		}
		
		pingSender = new PingSender();
		pingSender.start();
	}
	
	
	
	private class PingSender extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				try {
					String serialized = DJson.serialize(cluster.getStats());
					pingProducer.sendBytes(serialized.getBytes());
					Thread.sleep(1000);
				} catch (Exception e) {
					logger.error("Cluster Ping Sender Failed " + cluster.getNodeId() + " " + e.toString());
				}
				
			}
		}
	}
}
