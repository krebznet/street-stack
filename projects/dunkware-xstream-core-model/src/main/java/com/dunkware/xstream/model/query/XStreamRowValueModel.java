package com.dunkware.xstream.model.query;

public class XStreamRowValueModel {

	private XStreamValueType type;
	private String varIdent; 
	private String signalIdent; 
	private XStreamSessionTimeRange sessionTimeRange; 
	private XStreamHistoryTimeRange historicalTimeRange;
	private XStreamVarHistoricalAggFunc historicalAgg;
	
	
	public XStreamValueType getType() {
		return type;
	}
	public void setType(XStreamValueType type) {
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
	public XStreamVarHistoricalAggFunc getHistoricalAgg() {
		return historicalAgg;
	}
	public void setHistoricalAgg(XStreamVarHistoricalAggFunc historicalAgg) {
		this.historicalAgg = historicalAgg;
	}
	
	
	
	
	
	
	
	
}
