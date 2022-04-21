package com.dunkware.net.cluster.server.core;

import java.time.LocalDateTime;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.net.cluster.server.config.ClusterConfig;

public class ClusterNodeManager implements DKafkaByteHandler2  {
	
	@Autowired
	private ClusterConfig config; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private DKafkaByteConsumer2 pingConsumer;
	
	public void start(ClusterService cluster) throws Exception { 
		DKafkaByteConsumer2Spec spec = null;
		try {
			DKafkaByteConsumer2SpecBuilder.newBuilder(ConsumerType.Auto, OffsetType.Latest)
			.addBroker(config.getKafkaBrokers()).addTopic("cluster_core_ping").
			setClientAndGroup("ClusterPingConsumer" + DunkTime.formatHHMMSS(LocalDateTime.now()),
					DunkTime.formatHHMMSS(LocalDateTime.now())).build();
		 pingConsumer = DKafkaByteConsumer2.newInstance(spec);
		 pingConsumer.start();
		} catch (Exception e) {
			logger.error("Exception Starting Cluster Node Manager Kafka Cluster " + e.toString());
			throw e;
		}
	}

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		// okay what does the ping look like: ? JSON or GRPC 
		
	}
	
	
	
}
