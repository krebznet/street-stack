package com.dunkware.trade.broker.tws.connector.wrapper;

import com.ib.client.EWrapper;

public class WCOrderStatus implements EWrapperCall {
	
	private int orderId; 
	private String status; 
	private int filled; 
	private int remaining; 
	private double avgFillPrice; 
	private int permId; 
	private int parentId; 
	private double lastFillPrice; 
	private int clientId; 
	private String whyHeld; 
	
	 public WCOrderStatus( int orderId, String status, int filled, int remaining,
	            double avgFillPrice, int permId, int parentId, double lastFillPrice,
	            int clientId, String whyHeld)  {
		 this.orderId = orderId; 
		 this.status = status; 
		 this.filled = filled; 
		 this.remaining = remaining; 
		 this.avgFillPrice = avgFillPrice; 
		 this.permId = permId; 
		 this.parentId = parentId; 
		 this.lastFillPrice = lastFillPrice;
		 this.clientId = clientId; 
		 this.whyHeld = whyHeld; 
	 }


	@Override
	public void callMethod(EWrapper wrapper) {
		wrapper.orderStatus(orderId, status, filled, remaining, avgFillPrice, permId, parentId, lastFillPrice, clientId, whyHeld);
		
	}
	
	

}
