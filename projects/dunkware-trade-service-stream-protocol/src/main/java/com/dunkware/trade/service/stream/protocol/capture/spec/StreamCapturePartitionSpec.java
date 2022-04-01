package com.dunkware.trade.service.stream.protocol.capture.spec;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.trade.service.stream.protocol.store.spec.StreamStoreSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class StreamCapturePartitionSpec {

	private int id; 
	private String table; 
	private StreamStoreSpec store; 
	private List<TradeTickerSpec> tickers = new ArrayList<TradeTickerSpec>();
	
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public StreamStoreSpec getStore() {
		return store;
	}
	public void setStore(StreamStoreSpec store) {
		this.store = store;
	}
	public List<TradeTickerSpec> getTickers() {
		return tickers;
	}
	public void setTickers(List<TradeTickerSpec> tickers) {
		this.tickers = tickers;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
