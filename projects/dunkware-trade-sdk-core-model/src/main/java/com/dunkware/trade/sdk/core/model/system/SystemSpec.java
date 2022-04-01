package com.dunkware.trade.sdk.core.model.system;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.trade.sdk.core.model.trade.TradeSpec;

public class SystemSpec {

	private String identifier; 
	private SystemStatus status; 
	private List<TradeSpec> openTrades = new ArrayList<TradeSpec>();
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public SystemStatus getStatus() {
		return status;
	}
	public void setStatus(SystemStatus status) {
		this.status = status;
	}
	public List<TradeSpec> getOpenTrades() {
		return openTrades;
	}
	public void setOpenTrades(List<TradeSpec> openTrades) {
		this.openTrades = openTrades;
	}
	
	
	
}
