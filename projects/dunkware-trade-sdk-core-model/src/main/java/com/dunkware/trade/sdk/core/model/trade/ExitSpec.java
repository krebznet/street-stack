package com.dunkware.trade.sdk.core.model.trade;

import com.dunkware.common.util.dtime.DDateTime;

public class ExitSpec {

	private DDateTime closingTime; 
	private DDateTime closeTime; 
	
	private double avgFillPrice; 
	private int sharesFilled;
	
	private ExitStatus status; 
	
	private String exception;

	public DDateTime getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(DDateTime closingTime) {
		this.closingTime = closingTime;
	}

	public DDateTime getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(DDateTime closeTime) {
		this.closeTime = closeTime;
	}

	public ExitStatus getStatus() {
		return status;
	}

	public void setStatus(ExitStatus status) {
		this.status = status;
	}

	public double getAvgFillPrice() {
		return avgFillPrice;
	}

	public void setAvgFillPrice(double avgFillPrice) {
		this.avgFillPrice = avgFillPrice;
	}

	public int getSharesFilled() {
		return sharesFilled;
	}

	public void setSharesFilled(int sharesFilled) {
		this.sharesFilled = sharesFilled;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	} 
	
	
	
	
	
	
}
