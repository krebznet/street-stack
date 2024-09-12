package com.dunkware.trade.broker.api.model.order;

import com.dunkware.utils.core.observable.Observable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor()
@AllArgsConstructor
@ToString
public class OrderBean extends Observable<OrderBean> {

	
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
