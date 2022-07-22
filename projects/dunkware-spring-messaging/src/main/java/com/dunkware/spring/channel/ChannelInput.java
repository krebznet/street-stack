package com.dunkware.spring.channel;

public class ChannelInput {
	
	private String type; 
	private String broker; 
	private String consumerTopic; 
	private String producerTopic;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBroker() {
		return broker;
	}
	public void setBroker(String broker) {
		this.broker = broker;
	}
	public String getConsumerTopic() {
		return consumerTopic;
	}
	public void setConsumerTopic(String consumerTopic) {
		this.consumerTopic = consumerTopic;
	}
	public String getProducerTopic() {
		return producerTopic;
	}
	public void setProducerTopic(String producerTopic) {
		this.producerTopic = producerTopic;
	} 
	
	
	// ChannelService.createChannel("WorkerContainer", consumer,producer,brokers) 
	

}
