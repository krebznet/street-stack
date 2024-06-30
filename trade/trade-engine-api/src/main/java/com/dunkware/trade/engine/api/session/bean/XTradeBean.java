package com.dunkware.trade.engine.api.session.bean;

import com.dunkware.utils.core.observable.ObservableBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class XTradeBean extends ObservableBean {

	private double realizedPL; 
	private double unrealizedPL; 
	private int size; 
	private String symbol; 
	private String type; 
	private String side; 
	private String status; 
	private String strategy; 
	private String commission; 
}
