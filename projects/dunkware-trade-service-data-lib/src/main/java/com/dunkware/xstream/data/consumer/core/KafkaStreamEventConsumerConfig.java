package com.dunkware.xstream.data.consumer.core;

import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.xstream.data.consumer.StreamEventConsumerConfig;

public class KafkaStreamEventConsumerConfig extends StreamEventConsumerConfig {

	private DKafkaConsumerSpec2 consumerSpec;
	private DTimeZone timeZone;

	public KafkaStreamEventConsumerConfig(DKafkaConsumerSpec2 spec) {
		this.consumerSpec = spec;
	}

	public DKafkaConsumerSpec2 getConsumerSpec() {
		return consumerSpec;
	}

	public void setConsumerSpec(DKafkaConsumerSpec2 consumerSpec) {
		this.consumerSpec = consumerSpec;
	}

	public DTimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(DTimeZone timeZone) {
		this.timeZone = timeZone;
	}
	
	

}
