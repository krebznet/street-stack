package com.dunkware.street.smart.runtime.adapter;

import com.dunkware.utils.core.observable.ObservableBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionTrade extends ObservableBean{

	private int id; 
	private String symbol;
	private String status; 
	private int size; 
	private double marketValue; 
	private double unrealizedPL; 
	private double realizedPL; 
	private double commission; 
	private String strategy; 
	
}
