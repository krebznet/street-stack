package com.dunkware.trade.service.beach.server.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.dunkware.trade.sdk.core.model.order.OrderStatus;

@Entity(name = "BeaachSessionTradeOrderEntity")
@Table(name = "trade_session_order")
public class BeachSessionTradeOrderEntity {

	
	private String entryExit; 
	private BeachSessionTradeEntity trade; 
	private OrderStatus orderSatus; 
	private LocalDateTime submitTime; 
	private LocalDateTime fillTime; 
	private LocalDateTime cancelReqTime; 
	private LocalDateTime cancelTime; 
	private String rejectionMessage; 
	private String exceptionMessage; 
	
	private BeachSessionEntity session; 
	private BeachSystemEntity system; 
	
	
}
