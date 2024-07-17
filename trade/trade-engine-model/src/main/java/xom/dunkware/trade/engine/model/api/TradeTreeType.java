package com.dunkware.trade.engine.model.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeTreeType {

	private List<TradeValidatorType> tradeValidators;
	private List<TradeNodeType> nodes;

	
	
}
