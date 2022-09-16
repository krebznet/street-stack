package comm.dunkware.trade.service.beach.web.model;

import java.time.LocalDateTime;

public class BeachWebTrade {

	private long id; 
	private String status; 
	private String symbol; 
	private String account; 
	private String system; 
	private String side; 
	private int capitalAllocated;
	private int capitalFilled; 
	private int activeOrders; 
	private double marketValue; 
	private double gainLossAmount; 
	private double gainLossPercent;
	private String date; 
	private String openingTime; 
	private String openTime;
	private String closingTime;
	private String closeTime;; 
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Number getMarketValue() {
		return marketValue;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public int getCapitalAllocated() {
		return capitalAllocated;
	}
	public void setCapitalAllocated(int capitalAllocated) {
		this.capitalAllocated = capitalAllocated;
	}
	public int getCapitalFilled() {
		return capitalFilled;
	}
	public void setCapitalFilled(int capitalFilled) {
		this.capitalFilled = capitalFilled;
	}
	public int getActiveOrders() {
		return activeOrders;
	}
	public void setActiveOrders(int activeOrders) {
		this.activeOrders = activeOrders;
	}
	public double getGainLossAmount() {
		return gainLossAmount;
	}
	public void setGainLossAmount(double gainLossAmount) {
		this.gainLossAmount = gainLossAmount;
	}
	public double getGainLossPercent() {
		return gainLossPercent;
	}
	public void setGainLossPercent(double gainLossPercent) {
		this.gainLossPercent = gainLossPercent;
	}
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getOpeningTime() {
		return openingTime;
	}
	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	public void setMarketValue(double marketValue) {
		this.marketValue = marketValue;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	
	
	
	   
	
}
