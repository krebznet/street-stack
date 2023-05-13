package com.dunkware.xstream.model.search;

public class XStreamValue {

	private XStreamValueType type;
	private String varIdent; 
	private String signalIdent; 
	private XStreamSessionTimeRangeType sessionTimeRange; 
	private XStreamHIstoryTimeRangeTYpe historicalTimeRange;
	
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
	public XStreamSessionTimeRangeType getSessionTimeRange() {
		return sessionTimeRange;
	}
	public void setSessionTimeRange(XStreamSessionTimeRangeType sessionTimeRange) {
		this.sessionTimeRange = sessionTimeRange;
	}
	public XStreamHIstoryTimeRangeTYpe getHistoricalTimeRange() {
		return historicalTimeRange;
	}
	public void setHistoricalTimeRange(XStreamHIstoryTimeRangeTYpe historicalTimeRange) {
		this.historicalTimeRange = historicalTimeRange;
	} 
	
	
	
}
