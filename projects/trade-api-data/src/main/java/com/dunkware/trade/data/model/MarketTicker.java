package com.dunkware.trade.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Okay this is the model for a ticker, its designed to be light weight just enough detail
 * to understand how to resolve an exchange symbol based on the ticker type. 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketTicker {
	
	private String symbol; 
	private MarketTickerType type; 

}
