package com.dunkware.trade.api.broker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Broker Order Spec, this is the real deal right here, it specs out an order
 * that is a model and then translated into a real executable broker order or 
 * broker order preview. by design this is fully serialiazable, its the model 
 * or meta data around the type of oder you want to place. 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrokerOrderSpec {

	private int size; 
	// ticker shit right 
	private BrokerOrderType type; // SELL/BUY/SHORT
	private BrokerOrderAction action; // MARKET ETC. 
	private double stopPrice; 
	private double limitPrice; 
	private double trailingAmount; 
	private double trailingPercent; 
	
}
