package com.dunkware.trade.service.beach.model.trade;

import java.util.ArrayList;
import java.util.List;

public class BeachTradeModel {
	
	private String systemIdentifier; 
	private long systemId; 
	
	private String symbol; 
	
	private double capital; 
	
	private String side; // SHORT OR LONG
	private boolean enableEntryOffset; 
	private double entryOffsetPercent; 
	private boolean enableEntryTimeout; 
	private int entryTimeout; 
	private String entryTimeoutUnit; 
	private boolean enableEntryCap; 
	private double entryCapPercent; 
	
	private boolean enableStop; 
	private double stopPercent; 
	
	private List<BeachExitTriggerModel> exits = new ArrayList<BeachExitTriggerModel>();

	public String getSystemIdentifier() {
		return systemIdentifier;
	}

	public void setSystemIdentifier(String systemIdentifier) {
		this.systemIdentifier = systemIdentifier;
	}

	public long getSystemId() {
		return systemId;
	}

	public void setSystemId(long systemId) {
		this.systemId = systemId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public boolean isEnableEntryOffset() {
		return enableEntryOffset;
	}

	public void setEnableEntryOffset(boolean enableEntryOffset) {
		this.enableEntryOffset = enableEntryOffset;
	}

	public double getEntryOffsetPercent() {
		return entryOffsetPercent;
	}

	public void setEntryOffsetPercent(double entryOffsetPercent) {
		this.entryOffsetPercent = entryOffsetPercent;
	}

	public boolean isEnableEntryTimeout() {
		return enableEntryTimeout;
	}

	public void setEnableEntryTimeout(boolean enableEntryTimeout) {
		this.enableEntryTimeout = enableEntryTimeout;
	}

	public int getEntryTimeout() {
		return entryTimeout;
	}

	public void setEntryTimeout(int entryTimeout) {
		this.entryTimeout = entryTimeout;
	}

	public String getEntryTimeoutUnit() {
		return entryTimeoutUnit;
	}

	public void setEntryTimeoutUnit(String entryTimeoutUnit) {
		this.entryTimeoutUnit = entryTimeoutUnit;
	}

	public boolean isEnableEntryCap() {
		return enableEntryCap;
	}

	public void setEnableEntryCap(boolean enableEntryCap) {
		this.enableEntryCap = enableEntryCap;
	}

	public double getEntryCapPercent() {
		return entryCapPercent;
	}

	public void setEntryCapPercent(double entryCapPercent) {
		this.entryCapPercent = entryCapPercent;
	}

	public boolean isEnableStop() {
		return enableStop;
	}

	public void setEnableStop(boolean enableStop) {
		this.enableStop = enableStop;
	}

	public double getStopPercent() {
		return stopPercent;
	}

	public void setStopPercent(double stopPercent) {
		this.stopPercent = stopPercent;
	}

	public List<BeachExitTriggerModel> getExits() {
		return exits;
	}

	public void setExits(List<BeachExitTriggerModel> exits) {
		this.exits = exits;
	}
	
	

	
	
	
	
	
	

}
