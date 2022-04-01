package com.dunkware.common.kafka.properties;

import java.util.Properties;

import org.apache.kafka.clients.CommonClientConfigs;

import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.common.util.properties.DProperties;
import com.dunkware.common.util.uuid.DUUID;

public class DKafkaProperties {
	
	public static final String BOOTSTRAP_SERVERS = "bootstrap.servers";
	public static final String GROUP_ID_CONFIG = CommonClientConfigs.GROUP_ID_CONFIG;
	public static final String CLIENT_ID_CONFIG = CommonClientConfigs.CLIENT_ID_CONFIG;
	public static final String TOPICS = "topics";
	public static final String IDENTIFIER = "identifier";
	
	
	public static void addProducerProperties(Properties props) { 
		props.put("acks", "0");
		props.put("retries", 2);
		props.put("batch.size", 10000);
		props.put("linger.ms", 300);
		props.put("buffer.memory", 335544323);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "50000");
		props.put("session.timeout.ms", "30000");
	}
	
	
	
	public static void addConsumerProperties(Properties props) { 
		if(props.containsKey("acks") == false)
			props.put("acks", "all");
		if(props.containsKey("retries") == false)
			props.put("retries", 4);
		if(props.containsKey("batch.size") == false)
			props.put("batch.size", 5000);
		if(props.containsKey("linger.ms") == false)
			props.put("linger.ms", 1);
		if(props.containsKey("buffer.memory") == false)
			props.put("buffer.memory", 835544323);
		if(props.containsKey("auto.offset.reset") == false)
			props.put("auto.offset.reset", "earliest");
		props.put("heartbeat.interval.ms", "2500");
		// if 
		props.put("max.poll.records", 10000);
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "5000");
		props.put("session.timeout.ms", "50000");
		
		
		if(props.containsKey(GROUP_ID_CONFIG) == false) { 
			props.put(GROUP_ID_CONFIG, DUUID.randomUUID(5));
		}
		if(props.containsKey(CLIENT_ID_CONFIG) == false) { 
			props.put(CLIENT_ID_CONFIG, DUUID.randomUUID(5));
		}
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "15000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");

	}
}
