package com.dunkware.trade.broker.tws.connector.wrapper;

import com.ib.client.EWrapper;

public class WCTickEFP implements EWrapperCall {
	
	int tickerId; 
	int tickType;
	double basisPoints;
	String formattedBasisPoints; 
	double impliedFuture;
	int holdDays;
	String futureExpiry;
	double dividendImpact; 
	double dividendsToExpiry;
	
	public WCTickEFP(int tickerId, int tickType, double basisPoints,
			String formattedBasisPoints, double impliedFuture, int holdDays,
			String futureExpiry, double dividendImpact, double dividendsToExpiry)  {
		this.tickerId = tickerId; 
		this.tickType = tickType; 
		this.basisPoints = basisPoints;
		this.formattedBasisPoints = formattedBasisPoints;
		this.impliedFuture = impliedFuture;
		this.holdDays = holdDays;
		this.futureExpiry = futureExpiry; 
		this.dividendImpact = dividendImpact; 
		this.dividendsToExpiry = dividendsToExpiry;
		
	}

	@Override
	public void callMethod(EWrapper wrapper) {
		wrapper.tickEFP(tickerId, tickType, basisPoints, formattedBasisPoints, impliedFuture, holdDays, futureExpiry, dividendImpact, dividendsToExpiry);
		
	}

	
}
