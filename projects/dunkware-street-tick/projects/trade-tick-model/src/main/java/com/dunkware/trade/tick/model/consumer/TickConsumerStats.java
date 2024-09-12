package com.dunkware.trade.tick.model.consumer;

import java.util.List;

public class TickConsumerStats {

	private String kafkaTopic; 
	private int TickCount; 
	private List<String> symbols;
	private String consumerId;
	
	public String getKafkaTopic() {
		return kafkaTopic;
	}
	public void setKafkaTopic(String kafkaTopic) {
		this.kafkaTopic = kafkaTopic;
	}
	public int getTickCount() {
		return TickCount;
	}
	public void setTickCount(int tickCount) {
		TickCount = tickCount;
	}
	public List<String> getSymbols() {
		return symbols;
	}
	public void setSymbols(List<String> symbols) {
		this.symbols = symbols;
	}
	public String getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	} 
	
	
}
