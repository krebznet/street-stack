package com.dunkware.trade.sdk.core.model.order;

import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class OrderType {

	private TradeTickerSpec ticker;
	private int size = Integer.MIN_VALUE;
	private OrderAction action;
	private OrderKind kind; 
	private boolean outsiderth = false;
	private OrderStopTrigger stopTrigger; 
	private double trailingPercent = Double.MIN_VALUE;
	private double trailingStopPrice = Double.MIN_VALUE;
	
	public OrderType() { 
		
	}
	public TradeTickerSpec getTicker() {
		return ticker;
	}
	public void setTicker(TradeTickerSpec ticker) {
		this.ticker = ticker;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public OrderAction getAction() {
		return action;
	}
	public void setAction(OrderAction action) {
		this.action = action;
	}
	public OrderKind getKind() {
		return kind;
	}
	public void setKind(OrderKind kind) {
		this.kind = kind;
	}
	public boolean isOutsiderth() {
		return outsiderth;
	}
	public void setOutsiderth(boolean outsiderth) {
		this.outsiderth = outsiderth;
	}
	public OrderStopTrigger getStopTrigger() {
		return stopTrigger;
	}
	public void setStopTrigger(OrderStopTrigger stopTrigger) {
		this.stopTrigger = stopTrigger;
	}
	public double getTrailingPercent() {
		return trailingPercent;
	}
	public void setTrailingPercent(double trailingPercent) {
		this.trailingPercent = trailingPercent;
	}
	public double getTrailingStopPrice() {
		return trailingStopPrice;
	}
	public void setTrailingStopPrice(double trailingStopPrice) {
		this.trailingStopPrice = trailingStopPrice;
	}
	
	
	
	
	
	
	
	
}
