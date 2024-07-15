package com.dunkware.street.smart.runtime.adapter;

import com.dunkware.utils.core.observable.ObservableBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionMetrics extends ObservableBean {

	private double activeCapital;
	private double tradedCapital; 
	private double marketValue; 
	private double realizedPL;
	private double unrealizedPL;
	private int activeTradeCount;
	private int closedTradeCount; 
	private int openTradeCount;
	
}
