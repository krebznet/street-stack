package com.dunkware.xstream.model.signal;

import com.dunkware.xstream.model.query.XStreamEntityQueryModel;

public class XStreamSignalModel {

	private XStreamEntityQueryModel query; 
	private String identifier; 
	private int id; 
	private boolean enabledSymbolThrottle;
	private int symbolThrottle; 
	private boolean enableSymbolLimit; 
	private int symbolLimit; 
	private int scanInterval = 1;
	
	
	public XStreamEntityQueryModel getQuery() {
		return query;
	}
	public void setQuery(XStreamEntityQueryModel query) {
		this.query = query;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isEnabledSymbolThrottle() {
		return enabledSymbolThrottle;
	}
	public void setEnabledSymbolThrottle(boolean enabledSymbolThrottle) {
		this.enabledSymbolThrottle = enabledSymbolThrottle;
	}
	public int getSymbolThrottle() {
		return symbolThrottle;
	}
	public void setSymbolThrottle(int symbolThrottle) {
		this.symbolThrottle = symbolThrottle;
	}
	public boolean isEnableSymbolLimit() {
		return enableSymbolLimit;
	}
	public void setEnableSymbolLimit(boolean enableSymbolLimit) {
		this.enableSymbolLimit = enableSymbolLimit;
	}
	public int getSymbolLimit() {
		return symbolLimit;
	}
	public void setSymbolLimit(int symbolLimit) {
		this.symbolLimit = symbolLimit;
	}
	public int getScanInterval() {
		return scanInterval;
	}
	public void setScanInterval(int scanInterval) {
		this.scanInterval = scanInterval;
	} 
	
	
	
}
