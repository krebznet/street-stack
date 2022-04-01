package com.dunkware.trade.service.beach.protocol.trade.pool;

import com.dunkware.trade.sdk.core.model.trade.TradeType;

public class BeachPoolTradeReq {
	
	private TradeType trade;
	private String pool;
	
	public TradeType getTrade() {
		return trade;
	}
	public void setTrade(TradeType trade) {
		this.trade = trade;
	}
	public String getPool() {
		return pool;
	}
	public void setPool(String pool) {
		this.pool = pool;
	}
	
	
	
	
	
	
	
	

}
