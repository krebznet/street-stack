package com.dunkware.trade.broker.api;

import com.dunkware.utils.core.observable.ObservableBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor()
@AllArgsConstructor
@ToString
public class OrderBean extends ObservableBean {

	
	private String exception;
	private String symbol;
	private String tickerType;
	private boolean transmit;
	private String action;
	private String status;
	private int orderId;
	private int brokerOrderId;
	private int filled;
	private int remaining;
	private double comission;
	private double trailingStopPrice;
	private double trailingPercent;
	private String type;
	private int size;
	private double limitPrice;
	private String stopTrigger;
	
	
	
	
	


}
