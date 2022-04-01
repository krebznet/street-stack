package com.dunkware.trade.sdk.lib.model.signal;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.trade.sdk.core.model.system.SystemType;

public class SignalSystemType extends SystemType {

	private List<SignalTradeType> signals = new ArrayList<SignalTradeType>();

	private String kafkaTopic; 
	private String kafkaBrokers;
	
	public List<SignalTradeType> getSignals() {
		return signals;
	}
	public void setSignals(List<SignalTradeType> signals) {
		this.signals = signals;
	}
	public String getKafkaTopic() {
		return kafkaTopic;
	}
	public void setKafkaTopic(String kafkaTopic) {
		this.kafkaTopic = kafkaTopic;
	}
	public String getKafkaBrokers() {
		return kafkaBrokers;
	}
	public void setKafkaBrokers(String kafkaBrokers) {
		this.kafkaBrokers = kafkaBrokers;
	} 
	
	

}
