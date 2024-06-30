package com.dunkware.trade.api.session.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeBotBean {
	
	private String startTime; 
	private String status; 
	private String stopTime; 
	private Number activeCapital; 
	private Number tradedCapital; 
	private Number allocatedCapital; 
	private double realizedPL; 
	private double unrealizedPL; 
	private int activeTradeCount; 
	private int closedTradeCount; 
	private int missedTradeCount; 
	
	


}
