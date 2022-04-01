package com.dunkware.trade.service.beach.protocol.trade.pool;

import com.dunkware.trade.service.beach.protocol.trade.spec.BeachTradeSpec;

public class BeachPoolTradeResp {

	private String error; 
	private String code; 
	private BeachTradeSpec trade;
	
	public BeachPoolTradeResp() { 
		
	}
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public BeachTradeSpec getTrade() {
		return trade;
	}
	public void setTrade(BeachTradeSpec trade) {
		this.trade = trade;
	}
	
	
}
