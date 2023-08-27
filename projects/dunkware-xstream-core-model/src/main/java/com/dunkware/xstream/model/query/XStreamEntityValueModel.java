package com.dunkware.xstream.model.query;

public class XStreamEntityValueModel {

	private XStreamEntityValueType type;
	private String varIdent; 
	private String signalIdent; 
	private XStreamSessionTimeRange sessionTimeRange; 
	private XStreamHistoryTimeRange historicalTimeRange;
	private XStreamEntityVarAggHistType historicalAgg;
	private XStreamEntityVarAggType sessionAgg;
	
	
	public XStreamEntityValueType getType() {
		return type;
	}
	public void setType(XStreamEntityValueType type) {
		this.type = type;
	}
	public String getVarIdent() {
		return varIdent;
	}
	public void setVarIdent(String varIdent) {
		this.varIdent = varIdent;
	}
	public String getSignalIdent() {
		return signalIdent;
	}
	public void setSignalIdent(String signalIdent) {
		this.signalIdent = signalIdent;
	}
	public XStreamSessionTimeRange getSessionTimeRange() {
		return sessionTimeRange;
	}
	public void setSessionTimeRange(XStreamSessionTimeRange sessionTimeRange) {
		this.sessionTimeRange = sessionTimeRange;
	}
	public XStreamHistoryTimeRange getHistoricalTimeRange() {
		return historicalTimeRange;
	}
	public void setHistoricalTimeRange(XStreamHistoryTimeRange historicalTimeRange) {
		this.historicalTimeRange = historicalTimeRange;
	}
	public XStreamEntityVarAggHistType getHistoricalAgg() {
		return historicalAgg;
	}
	public void setHistoricalAgg(XStreamEntityVarAggHistType historicalAgg) {
		this.historicalAgg = historicalAgg;
	}
	public XStreamEntityVarAggType getSessionAgg() {
		return sessionAgg;
	}
	public void setSessionAgg(XStreamEntityVarAggType sessionAgg) {
		this.sessionAgg = sessionAgg;
	}
	
	
	
	
	
	
	
	
	
	
}
