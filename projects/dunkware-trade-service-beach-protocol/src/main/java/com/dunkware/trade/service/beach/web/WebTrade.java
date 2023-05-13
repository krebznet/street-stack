package com.dunkware.trade.service.beach.web;

public class WebTrade {
	
	private String status; 
	private String type; 
	private String openingTime; 
	private String closingTime; 
	private String openTime; 
	private String closeTime; 
	
	private int allocatedSize; 
	private String exception; 
	
	private double unrealizedPL; 
	private double realizedPL; 
	private double commission; 
	
	private String play; 
	private String account;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOpeningTime() {
		return openingTime;
	}
	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}
	public String getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	public int getAllocatedSize() {
		return allocatedSize;
	}
	public void setAllocatedSize(int allocatedSize) {
		this.allocatedSize = allocatedSize;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public double getUnrealizedPL() {
		return unrealizedPL;
	}
	public void setUnrealizedPL(double unrealizedPL) {
		this.unrealizedPL = unrealizedPL;
	}
	public double getRealizedPL() {
		return realizedPL;
	}
	public void setRealizedPL(double realizedPL) {
		this.realizedPL = realizedPL;
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
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
	
	

}
