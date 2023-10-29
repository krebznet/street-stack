package com.dunkware.trade.service.beach.model.system.strategy.signaltrade;

public class SignalTradeSummary {
	
	private String name; 
	private String signal; 
	private String stream; 
	private double allocatedCapital; 
	private double tradecapital; 
	private String side;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSignal() {
		return signal;
	}
	public void setSignal(String signal) {
		this.signal = signal;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public double getAllocatedCapital() {
		return allocatedCapital;
	}
	public void setAllocatedCapital(double allocatedCapital) {
		this.allocatedCapital = allocatedCapital;
	}
	public double getTradecapital() {
		return tradecapital;
	}
	public void setTradecapital(double tradecapital) {
		this.tradecapital = tradecapital;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	} 
	
	
	
	

}
