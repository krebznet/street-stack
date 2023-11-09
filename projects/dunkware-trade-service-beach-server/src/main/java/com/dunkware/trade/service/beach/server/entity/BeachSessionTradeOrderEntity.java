package com.dunkware.trade.service.beach.server.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dunkware.trade.sdk.core.model.order.OrderStatus;

@Entity(name = "BeaachSessionTradeOrderEntity")
@Table(name = "trade_session_order")
public class BeachSessionTradeOrderEntity {

	
	/*
	 * private long id; private String entryExit; private BeachSessionTradeEntity
	 * trade; private OrderStatus orderSatus; private LocalDateTime submitTime;
	 * private LocalDateTime fillTime; private LocalDateTime cancelReqTime; private
	 * LocalDateTime cancelTime; private String rejectionMessage; private String
	 * exceptionMessage;
	 * 
	 * private BeachSessionEntity session; private BeachSystemEntity system;
	 */
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	} 
	
	
	
}
