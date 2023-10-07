package com.dunkware.trade.domain.set;

public class WebPlay {
	
	private WebSummaryData summaryData; 
	private WebLimitsData limitsData; 
	private WebEntryOrderData entryOrderData; 
	private WebExitOrderData exitOrderData; 
	private WebExitTrigger[] exitTriggerData;
	public WebSummaryData getSummaryData() {
		return summaryData;
	}
	public void setSummaryData(WebSummaryData summaryData) {
		this.summaryData = summaryData;
	}
	public WebLimitsData getLimitsData() {
		return limitsData;
	}
	public void setLimitsData(WebLimitsData limitsData) {
		this.limitsData = limitsData;
	}
	public WebEntryOrderData getEntryOrderData() {
		return entryOrderData;
	}
	public void setEntryOrderData(WebEntryOrderData entryOrderData) {
		this.entryOrderData = entryOrderData;
	}
	public WebExitOrderData getExitOrderData() {
		return exitOrderData;
	}
	public void setExitOrderData(WebExitOrderData exitOrderData) {
		this.exitOrderData = exitOrderData;
	}
	public WebExitTrigger[] getExitTriggerData() {
		return exitTriggerData;
	}
	public void setExitTriggerData(WebExitTrigger[] exitTriggerData) {
		this.exitTriggerData = exitTriggerData;
	} 
	
	

}
