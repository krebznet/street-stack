package com.dunkware.xstream.core.extensions;

import com.dunkware.xstream.xproject.model.XStreamExtensionType;

public class StreamEventPublisherExtType extends XStreamExtensionType {
	
	private String kafkaBrokers; 
	private String snapshotTopic;
	private String signalTopic;
	private String timeTopic;
	private String kafkaIdentifier; 
	
	
	
	public String getKafkaBrokers() {
		return kafkaBrokers;
	}
	public void setKafkaBrokers(String kafkaBrokers) {
		this.kafkaBrokers = kafkaBrokers;
	}
	

	public String getSnapshotTopic() {
		return snapshotTopic;
	}
	public void setSnapshotTopic(String snapshotTopic) {
		this.snapshotTopic = snapshotTopic;
	}
	public String getSignalTopic() {
		return signalTopic;
	}
	public void setSignalTopic(String signalTopic) {
		this.signalTopic = signalTopic;
	}
	public String getKafkaIdentifier() {
		return kafkaIdentifier;
	}
	public void setKafkaIdentifier(String kafkaIdentifier) {
		this.kafkaIdentifier = kafkaIdentifier;
	}
	public String getTimeTopic() {
		return timeTopic;
	}
	public void setTimeTopic(String timeTopic) {
		this.timeTopic = timeTopic;
	}
	
	
	
	
	
	
	
	
	
	
	

}
