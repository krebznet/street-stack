package com.dunkware.trade.tick.service.protocol.ticker;

import com.dunkware.trade.tick.service.protocol.ticker.spec.TradeTickerListSpec;

public class TickerListGetResp {

	private String code;
	private int tickerCount;
	private String error;

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

	public TradeTickerListSpec getList() {
		return list;
	}

	public void setList(TradeTickerListSpec list) {
		this.list = list;
	}

	public int getTickerCount() {
		return tickerCount;
	}

	public void setTickerCount(int tickerCount) {
		this.tickerCount = tickerCount;
	}

}
