package com.dunkware.trade.service.beach.server.runtime;

import com.dunkware.common.util.observable.ObservableBean;

public class BeachTradeBean extends ObservableBean {
	
	private String accountIdent;
	private long accountId; 
	private long systemId; 
	private String systemIdent; 
	private int allocatedSize;
	private String play; 
	private int filledSize;
	private int playId; 
	private int id;
	private String symbol;
	private double upl; 
	private double rpl;
	private double uplp; 
	private double rplp; 
	private String openTime; 
	private String openingTime; 
	private String closeTime; 
	private String closingTime; 
	private String ident; 
	private int size; 
	private double marketValue; 
	private double entryValue; 
	private double entryCommission; 
	private double exitCommission; 
	private double tradeComission; 
	private String status;
	private int activeOrders;
	
	
	private String exception; 
	private String brokerException;
	
	public String getAccountIdent() {
		return accountIdent;
	}
	public void setAccountIdent(String accountIdent) {
		this.accountIdent = accountIdent;
	}
	public String getPlay() {
		return play;
	}
	public void setPlay(String play) {
		this.play = play;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public double getUpl() {
		return upl;
	}
	public void setUpl(double upl) {
		this.upl = upl;
	}
	public double getRpl() {
		return rpl;
	}
	public void setRpl(double rpl) {
		this.rpl = rpl;
	}
	public double getUplp() {
		return uplp;
	}
	public void setUplp(double uplp) {
		this.uplp = uplp;
	}
	public double getRplp() {
		return rplp;
	}
	public void setRplp(double rplp) {
		this.rplp = rplp;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getOpeningTime() {
		return openingTime;
	}
	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	public String getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public double getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(double marketValue) {
		this.marketValue = marketValue;
	}
	public double getEntryValue() {
		return entryValue;
	}
	public void setEntryValue(double entryValue) {
		this.entryValue = entryValue;
	}
	public double getEntryCommission() {
		return entryCommission;
	}
	public void setEntryCommission(double entryCommission) {
		this.entryCommission = entryCommission;
	}
	public double getExitCommission() {
		return exitCommission;
	}
	public void setExitCommission(double exitCommission) {
		this.exitCommission = exitCommission;
	}
	public double getTradeComission() {
		return tradeComission;
	}
	public void setTradeComission(double tradeComission) {
		this.tradeComission = tradeComission;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public int getAllocatedSize() {
		return allocatedSize;
	}
	public void setAllocatedSize(int allocatedSize) {
		this.allocatedSize = allocatedSize;
	}
	public int getPlayId() {
		return playId;
	}
	public void setPlayId(int playId) {
		this.playId = playId;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFilledSize() {
		return filledSize;
	}
	public void setFilledSize(int filledSize) {
		this.filledSize = filledSize;
	}
	public int getActiveOrders() {
		return activeOrders;
	}
	public void setActiveOrders(int activeOrders) {
		this.activeOrders = activeOrders;
	}
	public long getSystemId() {
		return systemId;
	}
	public void setSystemId(long systemId) {
		this.systemId = systemId;
	}
	public String getSystemIdent() {
		return systemIdent;
	}
	public void setSystemIdent(String systemIdent) {
		this.systemIdent = systemIdent;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public String getBrokerException() {
		return brokerException;
	}
	public void setBrokerException(String brokerException) {
		this.brokerException = brokerException;
	} 
	
	
	
	
	
	
	
	
	

}
