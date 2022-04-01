package com.dunkware.trade.service.beach.protocol.trade.spec;

import com.dunkware.trade.sdk.core.model.trade.TradeSpec;

public class BeachTradeSpec extends TradeSpec {
	
	private String broker; 
	private String account; 
	private String pool; 
	
	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getBroker() {
		return broker;
	}
	
	public void setBroker(String broker) {
		this.broker = broker;
	}

	public String getPool() {
		return pool;
	}

	public void setPool(String pool) {
		this.pool = pool;
	}
	
	
	
	
	
	
}
