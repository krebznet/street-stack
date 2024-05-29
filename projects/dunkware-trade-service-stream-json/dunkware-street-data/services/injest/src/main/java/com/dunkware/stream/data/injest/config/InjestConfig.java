package com.dunkware.stream.data.injest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;


@Service
public class InjestConfig {
	
	@Value("${consumers.kafka.brokers}")
	private String kafkaBrokers;
	@Value("${consumers.kafka.consumer-id}")
	private String kafkaConsumerId;
	@Value("${consumers.kafka.consumer-group}")
	private String kafkaConsumerGroup;
	@Value("${entity.stats.consumer.loader.batch-size}")
	private int sessionEntityStatLoaderBatchSize;
	@Value("${entity.stats.consumer.loader.thread-count}")
	private int sessionEntityStatLoaderThreadCount;
	
	@PostConstruct
	private void init() {
		
	}
	
	
	public String getKafkaBrokers() {
		return kafkaBrokers;
	}
	public String getKafkaConsumerId() {
		return kafkaConsumerId;
	}
	public String getKafkaConsumerGroup() {
		return kafkaConsumerGroup;
	}
	public int getSessionEntityStatLoaderBatchSize() {
		return sessionEntityStatLoaderBatchSize;
	}
	public int getSessionEntityStatLoaderThreadCount() {
		return sessionEntityStatLoaderThreadCount;
	}
	
	
	
	
			

}
