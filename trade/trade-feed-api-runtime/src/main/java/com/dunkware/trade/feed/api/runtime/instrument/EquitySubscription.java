package com.dunkware.trade.feed.api.runtime.instrument;

import com.dunkware.utils.core.observable.Observable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquitySubscription extends Observable<EquitySubscription> {

	private String symbol;
	private double last; 
	private long volume; 
	private int tradeCount;
	
	public void setLast(double last) { 
		double old = this.last;
		this.last = last; 
		propertyChange("last", old, last);
	}
	
	public void setVolume(long volume) { 
		propertyChange("volume",this.volume,volume);
		this.volume = volume;
	}
	
	public void setTradeCount(int tradeCount) { 
		propertyChange("tradeCount", this.tradeCount, tradeCount);
		this.tradeCount = tradeCount;
	}
}
