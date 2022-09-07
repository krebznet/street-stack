package comm.dunkware.trade.service.beach.web.model;

import java.time.LocalDateTime;

public class WebTrade {

	private long tradeId; 
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
	private LocalDateTime openingTimestamp;
	private LocalDateTime openTimestamp;
	private LocalDateTime closingTimestamp;
	private LocalDateTime closeTImestamp; 
	
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
	public long getTradeId() {
		return tradeId;
	}
	public void setTradeId(long tradeId) {
		this.tradeId = tradeId;
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
	public LocalDateTime getOpeningTimestamp() {
		return openingTimestamp;
	}
	public void setOpeningTimestamp(LocalDateTime openingTimestamp) {
		this.openingTimestamp = openingTimestamp;
	}
	public LocalDateTime getOpenTimestamp() {
		return openTimestamp;
	}
	public void setOpenTimestamp(LocalDateTime openTimestamp) {
		this.openTimestamp = openTimestamp;
	}
	public LocalDateTime getClosingTimestamp() {
		return closingTimestamp;
	}
	public void setClosingTimestamp(LocalDateTime closingTimestamp) {
		this.closingTimestamp = closingTimestamp;
	}
	public LocalDateTime getCloseTImestamp() {
		return closeTImestamp;
	}
	public void setCloseTImestamp(LocalDateTime closeTImestamp) {
		this.closeTImestamp = closeTImestamp;
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
