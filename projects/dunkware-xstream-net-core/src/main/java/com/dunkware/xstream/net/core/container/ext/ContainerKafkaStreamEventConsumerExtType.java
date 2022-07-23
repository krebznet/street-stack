package com.dunkware.xstream.net.core.container.ext;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.xstream.container.ContainerExtType;

public class ContainerKafkaStreamEventConsumerExtType extends ContainerExtType {

	private String kafkaBrokers;
	private String kafkaTopic;
	private String kafkaConsumerId;
	
	private DTimeZone timeZone; 
	
	
	public String getKafkaBrokers() {
		return kafkaBrokers;
	}

	public void setKafkaBrokers(String kafkaBrokers) {
		this.kafkaBrokers = kafkaBrokers;
	}

	public String getKafkaTopic() {
		return kafkaTopic;
	}

	public void setKafkaTopic(String kafkaTopic) {
		this.kafkaTopic = kafkaTopic;
	}

	public String getKafkaConsumerId() {
		return kafkaConsumerId;
	}

	public void setKafkaConsumerId(String kafkaConsumerId) {
		this.kafkaConsumerId = kafkaConsumerId;
	}

	public DTimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(DTimeZone timeZone) {
		this.timeZone = timeZone;
	}
	
	
	
}