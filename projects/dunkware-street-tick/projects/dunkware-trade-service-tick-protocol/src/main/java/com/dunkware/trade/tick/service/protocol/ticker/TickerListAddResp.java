package com.dunkware.trade.tick.service.protocol.ticker;

import com.dunkware.trade.tick.service.protocol.ticker.spec.TradeTickerListSpec;

public class TickerListAddResp {

	private String code; 
	private String error; 
	private long id; 
	private int size;
	private TradeTickerListSpec list;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public TradeTickerListSpec getList() {
		return list;
	}
	public void setList(TradeTickerListSpec list) {
		this.list = list;
	}
	
	
	
}
