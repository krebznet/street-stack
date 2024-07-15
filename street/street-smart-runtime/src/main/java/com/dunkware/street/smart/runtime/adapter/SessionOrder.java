package com.dunkware.street.smart.runtime.adapter;

import com.dunkware.utils.core.observable.ObservableBean;

public class SessionOrder extends ObservableBean{

	private int tradeId; 
	private String ticker;
	private String orderType;
	private String tradeSource;
	private String symbol;
	private String status; 
	private int size;
	private int remaining;
	private int filled; 
	private String error; 
	private double commision;
	private String strategy;
	 
	
}