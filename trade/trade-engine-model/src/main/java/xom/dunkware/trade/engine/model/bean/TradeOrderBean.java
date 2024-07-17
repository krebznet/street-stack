package com.dunkware.trade.engine.model.bean;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TradeOrderBean {

	private LocalDateTime submittedTime;
	private LocalDateTime preSubmittedTime; 
	private LocalDateTime cancelRequested; 
	private LocalDateTime canceledTime;
	
	private String tradeKey;
	private String tradeExecutorSide;
	
	private double commision; 
	
	private int size; 
	private int filled; 
	private int remaining; 
	
	private String rejection; 
	private String errpr;
	
	private String status; 
	private String type; // SELL/BUY/SHORT
	private String action; 
	private Double stopPrice = null;
	private Double limitPrice = null; 
	private Double trailingAmount = null; 
	private Double trailingPercent = null;
}
