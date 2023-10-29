package com.dunkware.trade.service.beach.server.session;

import java.time.LocalDateTime;

public class BeachSessionTradeOrderBean {
	
	private String status; 
	private int filled; 
	private LocalDateTime submitTime;  
	private LocalDateTime filledTime; 
	private int size; 
	private LocalDateTime cancelReqTime; 
	private LocalDateTime cancelTie; 
	private String tradeIdentifier; 
	private String strategyIdentifier; 
	private String entryExit; 
	private double commission;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getFilled() {
		return filled;
	}
	public void setFilled(int filled) {
		this.filled = filled;
	}
	public LocalDateTime getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(LocalDateTime submitTime) {
		this.submitTime = submitTime;
	}
	public LocalDateTime getFilledTime() {
		return filledTime;
	}
	public void setFilledTime(LocalDateTime filledTime) {
		this.filledTime = filledTime;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public LocalDateTime getCancelReqTime() {
		return cancelReqTime;
	}
	public void setCancelReqTime(LocalDateTime cancelReqTime) {
		this.cancelReqTime = cancelReqTime;
	}
	public LocalDateTime getCancelTie() {
		return cancelTie;
	}
	public void setCancelTie(LocalDateTime cancelTie) {
		this.cancelTie = cancelTie;
	}
	public String getTradeIdentifier() {
		return tradeIdentifier;
	}
	public void setTradeIdentifier(String tradeIdentifier) {
		this.tradeIdentifier = tradeIdentifier;
	}
	public String getStrategyIdentifier() {
		return strategyIdentifier;
	}
	public void setStrategyIdentifier(String strategyIdentifier) {
		this.strategyIdentifier = strategyIdentifier;
	}
	public String getEntryExit() {
		return entryExit;
	}
	public void setEntryExit(String entryExit) {
		this.entryExit = entryExit;
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	} 

	
	

}
