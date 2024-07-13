package com.dunkware.street.stream.model.session.openers;

import com.dunkware.street.stream.model.session.TradeOpenerType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketOpenerType extends TradeOpenerType {

	private boolean enableTimeout = false; 
	private int timeout; 
	
}
