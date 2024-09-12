package com.dunkware.trade.broker.api.model.order;

import java.beans.Transient;

import com.dunkware.trade.feed.api.model.ticker.Ticker;

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
public class OrderType {

	private int size; 
	// ticker shit right 
	private OrderKind kind; // SELL/BUY/SHORT
	private OrderSide side; // MARKET ETC. 
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
		if(kind == null) { 
			throw new Exception("Brokrer order kind is not set ");
		}
		if(side == null) { 
			throw new Exception("Broker side is not set ");
		}
		if(kind ==  OrderKind.TRAIL_PERCENT) { 
			if(trailingPercent == null ) {
				throw new Exception("Order type trail percent does not have trailing percent set");
			}
		}
		if(kind == OrderKind.TRAIL_AMOUNT) { 
			if(trailingAmount == null) { 
				throw new Exception("Trailing Amount does not have amount set");
			}
		}
		
	}
}
