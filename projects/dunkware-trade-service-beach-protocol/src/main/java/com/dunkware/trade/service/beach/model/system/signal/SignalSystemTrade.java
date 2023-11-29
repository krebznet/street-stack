package com.dunkware.trade.service.beach.model.system.signal;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.trade.service.beach.model.system.common.TradeEntryOrderData;
import com.dunkware.trade.service.beach.model.system.common.TradeExitOrderData;
import com.dunkware.trade.service.beach.model.system.common.TradeExitTrigger;
import com.dunkware.trade.service.beach.model.system.common.TradeSide;

public class SignalSystemTrade {
	
	private String name; 
	private TradeSide side;// LONG || SHORT 
	private List<Integer> signals = new ArrayList<Integer>();
	private int weight; 
	private double tradeCapital; // 5,000  
	private double tradeCapitalLimit;  // 15,00
	private boolean enableActiveTradeLimit = false; 
	private int activeTradeLimit; 
	private boolean enableActiveSymbolLimit; 
	private int activeSymbolLimit; 
	private boolean enableStopLoss; 
	private double stopLoss; 
	private TradeEntryOrderData entryOrderData;
	private TradeExitOrderData exitOrderData; 
	private List<TradeExitTrigger> exitTriggers = new ArrayList<TradeExitTrigger>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Integer> getSignals() {
		return signals;
	}
	public void setSignals(List<Integer> signals) {
		this.signals = signals;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public double getTradeCapital() {
		return tradeCapital;
	}
	public void setTradeCapital(double tradeCapital) {
		this.tradeCapital = tradeCapital;
	}
	public double getTradeCapitalLimit() {
		return tradeCapitalLimit;
	}
	public void setTradeCapitalLimit(double tradeCapitalLimit) {
		this.tradeCapitalLimit = tradeCapitalLimit;
	}
	public boolean isEnableActiveTradeLimit() {
		return enableActiveTradeLimit;
	}
	public void setEnableActiveTradeLimit(boolean enableActiveTradeLimit) {
		this.enableActiveTradeLimit = enableActiveTradeLimit;
	}
	public int getActiveTradeLimit() {
		return activeTradeLimit;
	}
	public void setActiveTradeLimit(int activeTradeLimit) {
		this.activeTradeLimit = activeTradeLimit;
	}
	public boolean isEnableActiveSymbolLimit() {
		return enableActiveSymbolLimit;
	}
	public void setEnableActiveSymbolLimit(boolean enableActiveSymbolLimit) {
		this.enableActiveSymbolLimit = enableActiveSymbolLimit;
	}
	public int getActiveSymbolLimit() {
		return activeSymbolLimit;
	}
	public void setActiveSymbolLimit(int activeSymbolLimit) {
		this.activeSymbolLimit = activeSymbolLimit;
	}
	public boolean isEnableStopLoss() {
		return enableStopLoss;
	}
	public void setEnableStopLoss(boolean enableStopLoss) {
		this.enableStopLoss = enableStopLoss;
	}
	public double getStopLoss() {
		return stopLoss;
	}
	public void setStopLoss(double stopLoss) {
		this.stopLoss = stopLoss;
	}
	public TradeEntryOrderData getEntryOrderData() {
		return entryOrderData;
	}
	public void setEntryOrderData(TradeEntryOrderData entryOrderData) {
		this.entryOrderData = entryOrderData;
	}
	public TradeExitOrderData getExitOrderData() {
		return exitOrderData;
	}
	public void setExitOrderData(TradeExitOrderData exitOrderData) {
		this.exitOrderData = exitOrderData;
	}
	public List<TradeExitTrigger> getExitTriggers() {
		return exitTriggers;
	}
	public void setExitTriggers(List<TradeExitTrigger> exitTriggers) {
		this.exitTriggers = exitTriggers;
	}
	public TradeSide getSide() {
		return side;
	}
	public void setSide(TradeSide side) {
		this.side = side;
	}
	
	
	
	
	
	

}
