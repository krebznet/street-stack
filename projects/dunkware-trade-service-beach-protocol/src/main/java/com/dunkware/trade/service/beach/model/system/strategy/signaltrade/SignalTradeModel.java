package com.dunkware.trade.service.beach.model.system.strategy.signaltrade;

import com.dunkware.trade.service.beach.model.system.common.StrategyExitTriggerModel;
import com.dunkware.trade.service.beach.model.system.common.TradeConstraitsModel;

public class SignalTradeModel {
	
	private String type; 
	private double allocatedCapital; 
	private int weight; 
	
	private SignalTradeSummary summaryData; 
	private TradeConstraitsModel constrains; 
	
	private WebEntryOrderData entryOrderData; 
	private WebExitOrderData exitOrderData; 
	private StrategyExitTriggerModel[] exitTriggerData;
	
	public SignalTradeSummary getSummaryData() {
		return summaryData;
	}
	public void setSummaryData(SignalTradeSummary summaryData) {
		this.summaryData = summaryData;
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
	public StrategyExitTriggerModel[] getExitTriggerData() {
		return exitTriggerData;
	}
	public void setExitTriggerData(StrategyExitTriggerModel[] exitTriggerData) {
		this.exitTriggerData = exitTriggerData;
	} 
	
	

}
