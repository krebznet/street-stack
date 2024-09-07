package com.dunkware.trade.feed.model.ticker;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Ticker {
 
	private String symbol; 
	private TickerType type; 
	
	public static Ticker usEquity(String symbol) { 
		Ticker ticker = new Ticker();
		ticker.setSymbol(symbol);
		ticker.setType(TickerType.Equity);
		return ticker;
	}
	
}
