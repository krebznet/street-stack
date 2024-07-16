package com.dunkware.trade.strat.tradebook.model.api;

import com.dunkware.trade.engine.model.api.TradeEntryTriggerType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignalEntryType extends TradeEntryTriggerType {
	
	private String name; 
	private String stream; 
	private String signal; 

}
