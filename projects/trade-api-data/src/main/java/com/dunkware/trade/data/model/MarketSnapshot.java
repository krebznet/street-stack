package com.dunkware.trade.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *This is a snapshot of a market ticker, depending on the ticker type depends on what fields 
 *will be set, this covers equities, options, futures, cryptos etc. any value of
 *MarketTickerType
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketSnapshot  {

	private String symbol; 
	private int timestamp; 
	private double low; 
	private double high; 
	private long volume; 
	private int tradeCount; 
	private double price; 
	
}
