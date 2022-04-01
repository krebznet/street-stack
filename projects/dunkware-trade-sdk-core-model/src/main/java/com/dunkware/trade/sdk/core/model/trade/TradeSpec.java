package com.dunkware.trade.sdk.core.model.trade;

public class TradeSpec {

	private TradeStatus status; 
	private EntrySpec entry; 
	private ExitSpec exit; 
	private TradeType type; 
	
	private double unrealizedPL; 
	private double realizedPL;
	
	private int allocatedSize; 
	
	private String exception;
	
	public TradeStatus getStatus() {
		return status;
	}
	public void setStatus(TradeStatus status) {
		this.status = status;
	}
	public EntrySpec getEntry() {
		return entry;
	}
	public void setEntry(EntrySpec entry) {
		this.entry = entry;
	}
	public ExitSpec getExit() {
		return exit;
	}
	public void setExit(ExitSpec exit) {
		this.exit = exit;
	}
	public TradeType getType() {
		return type;
	}
	public void setType(TradeType type) {
		this.type = type;
	}
	public double getUnrealizedPL() {
		return unrealizedPL;
	}
	public void setUnrealizedPL(double unrealizedPL) {
		this.unrealizedPL = unrealizedPL;
	}
	public double getRealizedPL() {
		return realizedPL;
	}
	public void setRealizedPL(double realizedPL) {
		this.realizedPL = realizedPL;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public int getAllocatedSize() {
		return allocatedSize;
	}
	public void setAllocatedSize(int allocatedSize) {
		this.allocatedSize = allocatedSize;
	} 
	
	
	
	
	
	
}
