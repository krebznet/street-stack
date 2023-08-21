package com.dunkware.trade.service.beach.server.runtime;

import com.dunkware.common.util.observable.ObservableBean;

public class BeachOrderBean extends ObservableBean {
	
	private String play; 
	private String account; 
	private int accountId;
	private String trade; 
	private String status; 
	private int filled; 
	private int remaining; 
	private int size; 
	private double commission;
	private int id; 
	private String symbol;
	private String kind; 
	private double avgFillPrice;
	private String openTimeStamp;
	private String fillTimeStamp;
	
	public String getPlay() {
		return play;
	}
	public void setPlay(String play) {
		this.play = play;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
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
	public int getRemaining() {
		return remaining;
	}
	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public double getAvgFillPrice() {
		return avgFillPrice;
	}
	public void setAvgFillPrice(double avgFillPrice) {
		this.avgFillPrice = avgFillPrice;
	}
	public String getOpenTimeStamp() {
		return openTimeStamp;
	}
	public void setOpenTimeStamp(String openTimeStamp) {
		this.openTimeStamp = openTimeStamp;
	}
	public String getFillTimeStamp() {
		return fillTimeStamp;
	}
	public void setFillTimeStamp(String fillTimeStamp) {
		this.fillTimeStamp = fillTimeStamp;
	} 
	
	
	
	

}
