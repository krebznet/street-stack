package com.dunkware.trade.sdk.core.model.order;

import com.dunkware.common.util.dtime.DZonedDateTime;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class OrderSpec {
	
	private double stopPrice;
	private OrderKind kind; 
	private int size; 
	private int filled;
	private OrderAction action;
	private double limitPrice; 
	private double trailingAmount; 
	private double trailingPercent; 
	private double trailingStopPrice;
	private boolean outsideRth; 
	private boolean whatif; 
	private boolean transmit;
	private OrderStopTrigger stopTrigger;
	private TradeTickerSpec ticker;
	private OrderStatus status;
	private int remaining; 
	private double avgFillPrice; 
	private double commision; 
	private String exception; 
	private DZonedDateTime openDateTime = null;
	private DZonedDateTime submitDateTime = null;
	private DZonedDateTime fillDateTime = null;
	private DZonedDateTime cancelReqDateTime= null;
	private DZonedDateTime cancelDateTime = null;
	private DZonedDateTime lastUpdate;
	private int id;
	
	public OrderSpec() { 
		
	}
	
	public double getStopPrice() {
		return stopPrice;
	}
	public void setStopPrice(double stopPrice) {
		this.stopPrice = stopPrice;
	}
	public OrderKind getKind() {
		return kind;
	}
	public void setKind(OrderKind kind) {
		this.kind = kind;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getFilled() {
		return filled;
	}
	public void setFilled(int filled) {
		this.filled = filled;
	}
	public OrderAction getAction() {
		return action;
	}
	public void setAction(OrderAction action) {
		this.action = action;
	}
	public double getLimitPrice() {
		return limitPrice;
	}
	public void setLimitPrice(double limitPrice) {
		this.limitPrice = limitPrice;
	}
	public double getTrailingAmount() {
		return trailingAmount;
	}
	public void setTrailingAmount(double trailingAmount) {
		this.trailingAmount = trailingAmount;
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
	public boolean isOutsideRth() {
		return outsideRth;
	}
	public void setOutsideRth(boolean outsideRth) {
		this.outsideRth = outsideRth;
	}
	public boolean isWhatif() {
		return whatif;
	}
	public void setWhatif(boolean whatif) {
		this.whatif = whatif;
	}
	public boolean isTransmit() {
		return transmit;
	}
	public void setTransmit(boolean transmit) {
		this.transmit = transmit;
	}
	public OrderStopTrigger getStopTrigger() {
		return stopTrigger;
	}
	public void setStopTrigger(OrderStopTrigger stopTrigger) {
		this.stopTrigger = stopTrigger;
	}
	public TradeTickerSpec getTicker() {
		return ticker;
	}
	public void setTicker(TradeTickerSpec ticker) {
		this.ticker = ticker;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public int getRemaining() {
		return remaining;
	}
	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}
	public double getAvgFillPrice() {
		return avgFillPrice;
	}
	public void setAvgFillPrice(double avgFillPrice) {
		this.avgFillPrice = avgFillPrice;
	}
	public double getCommision() {
		return commision;
	}
	public void setCommision(double commision) {
		this.commision = commision;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public DZonedDateTime getOpenDateTime() {
		return openDateTime;
	}
	public void setOpenDateTime(DZonedDateTime openDateTime) {
		this.openDateTime = openDateTime;
	}
	public DZonedDateTime getSubmitDateTime() {
		return submitDateTime;
	}
	public void setSubmitDateTime(DZonedDateTime submitDateTime) {
		this.submitDateTime = submitDateTime;
	}
	public DZonedDateTime getFillDateTime() {
		return fillDateTime;
	}
	public void setFillDateTime(DZonedDateTime fillDateTime) {
		this.fillDateTime = fillDateTime;
	}
	public DZonedDateTime getCancelReqDateTime() {
		return cancelReqDateTime;
	}
	public void setCancelReqDateTime(DZonedDateTime cancelReqDateTime) {
		this.cancelReqDateTime = cancelReqDateTime;
	}
	public DZonedDateTime getCancelDateTime() {
		return cancelDateTime;
	}
	public void setCancelDateTime(DZonedDateTime cancelDateTime) {
		this.cancelDateTime = cancelDateTime;
	}
	public DZonedDateTime getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(DZonedDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	} 
	
	

}
