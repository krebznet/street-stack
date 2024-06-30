package com.dunkware.trade.broker.api;

import java.beans.Transient;

import com.dunkware.trade.api.data.ticker.Ticker;

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
public class OrderSpec {

	private int size; 
	// ticker shit right 
	private OrderType type; // SELL/BUY/SHORT
	private OrderAction action; // MARKET ETC. 
	private Double stopPrice = null;
	private Double limitPrice = null; 
	private Double trailingAmount = null; 
	private Double trailingPercent = null;
	
	private Ticker ticker; 
	
	
	@Transient
	public void validate() throws Exception { 
		if(ticker == null) { 
			throw new Exception("Ticker not set"); 
		}
		if(ticker.getType() == null) { 
			throw new Exception("Ticker Type not set");
		}
		if(type == null) { 
			throw new Exception("Brokrer order type is not set ");
		}
		if(action == null) { 
			throw new Exception("Broker action is not set ");
		}
		if(type ==  OrderType.TRAIL_PERCENT) { 
			if(trailingPercent == null ) {
				throw new Exception("Order type trail percent does not have trailing percent set");
			}
		}
		if(type == OrderType.TRAIL_AMOUNT) { 
			if(trailingAmount == null) { 
				throw new Exception("Trailing Amount does not have amount set");
			}
		}
		
	}
}
