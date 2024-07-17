package com.dunkware.trade.engine.model.trade;

import com.dunkware.trade.api.data.ticker.Ticker;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TradeRequest {
	
	private Ticker ticker; 
	private TradeSide side; 
	private double capital; 
	private String strategy;

}
