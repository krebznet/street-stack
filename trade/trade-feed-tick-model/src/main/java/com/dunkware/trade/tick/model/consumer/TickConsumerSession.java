package com.dunkware.trade.tick.model.consumer;

public class TickConsumerSession {
	
	private int activeSubscriptions; 
	private int inactiveSubscriptions; 
	private String kafkaBroker; 
	private String kafkaTopic; 
	private String sessionId; 
	
	public TickConsumerSession() { 
		
	}

	public int getActiveSubscriptions() {
		return activeSubscriptions;
	}

	public void setActiveSubscriptions(int activeSubscriptions) {
		this.activeSubscriptions = activeSubscriptions;
	}

	public int getInactiveSubscriptions() {
		return inactiveSubscriptions;
	}

	public void setInactiveSubscriptions(int inactiveSubscriptions) {
		this.inactiveSubscriptions = inactiveSubscriptions;
	}

	public String getKafkaBroker() {
		return kafkaBroker;
	}

	public void setKafkaBroker(String kafkaBroker) {
		this.kafkaBroker = kafkaBroker;
	}

	public String getKafkaTopic() {
		return kafkaTopic;
	}

	public void setKafkaTopic(String kafkaTopic) {
		this.kafkaTopic = kafkaTopic;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	

}
