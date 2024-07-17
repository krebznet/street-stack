package com.dunkware.trade.engine.model.api.node;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.trade.engine.model.api.TradeNodeType;

public class TradePortfolioType extends TradeNodeType {

	private String name; 
	private TradeNodeType parent;
	private List<TradeNodeType> children = new ArrayList<TradeNodeType>();
	
}
