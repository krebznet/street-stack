package com.dunkware.trade.sdk.core.runtime.order.builder;

import com.dunkware.trade.sdk.core.model.order.OrderAction;
import com.dunkware.trade.sdk.core.model.order.OrderKind;
import com.dunkware.trade.sdk.core.model.order.OrderStopTrigger;
import com.dunkware.trade.sdk.core.model.order.OrderType;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class OrderTypeBuilder {

	public static OrderTypeBuilder newInstance() {
		return new OrderTypeBuilder();
	}

	OrderType type = new OrderType();
	
	private OrderTypeBuilder() {

	}

	public OrderTypeBuilder size(int size) {
		type.setSize(size);
		return this; 
	}
	
	public OrderTypeBuilder action(OrderAction action) { 
		type.setAction(action);
		return this;
	}
	
	public OrderTypeBuilder kind(OrderKind kind) { 
		this.type.setKind(kind);
		return this; 
	}
	
	public OrderTypeBuilder outsideRth(boolean value) { 
		this.type.setOutsiderth(value); 
		return this;
	}
	
	public OrderTypeBuilder ticker(TradeTickerSpec ticker) { 
		this.type.setTicker(ticker);
		return this;
	}
	
	public OrderTypeBuilder stopTrigger(OrderStopTrigger trigger) { 
		this.type.setStopTrigger(trigger);
		return this; 
	}
	
	public OrderTypeBuilder trailingPercent(double percent) { 
		this.type.setTrailingPercent(percent);
		return this;
	}
	
	public OrderTypeBuilder trailingStopPrice(double price) { 
		this.type.setTrailingStopPrice(price);
		return this;
	}
	
	public OrderType build() throws OrderTypeBuilderException {
		if(this.type.getTicker() == null) { 
			throw new OrderTypeBuilderException("Ticker Not Set On Order Builder");
		}
		if(this.type.getAction() == null) { 
			throw new OrderTypeBuilderException("Order Action Not Set On Order Builder");
		}
		if(this.type.getKind() == null) { 
			throw new OrderTypeBuilderException("Order Kind Not Set On Order Builder");
		}
		if(this.type.getSize() == 0 || this.type.getSize() < 0) { 
			throw new OrderTypeBuilderException("Order Size Not Set On Order Builder");
		}
		if(this.type.getKind() == OrderKind.TRAIL_PERCENT || this.type.getKind() == OrderKind.TRAIL_AMOUNT) { 
			if(this.type.getStopTrigger() == null) { 
				throw new OrderTypeBuilderException("Order Stop Trigger Not Set On Stop Order Kind On Order Builder");
			}
		}
		if(this.type.getKind() == OrderKind.TRAIL_PERCENT) {
			if(this.type.getTrailingPercent() == Double.MIN_VALUE) { 
				throw new OrderTypeBuilderException("Trailing Percent Is Not Set on Order Kind TRAIL_PERCENT");
			}
		}
		return type;
	}

}
