package com.dunkware.trade.sdk.core.model.trade;

import com.dunkware.common.util.dtime.DDateTime;

public class EntrySpec {

	private EntryStatus status; 
	private DDateTime openingTime; 
	private DDateTime openTime; 
	private DDateTime cancellingTime; 
	private DDateTime cancelledTime; 
	
	private String exception; 
	
	private int allocatedSize; 
	private int filledSize; 
	
	private double avgFillPrice; 
	
	private double commission; 
	
	private EntryType type;

	public EntryStatus getStatus() {
		return status;
	}

	public void setStatus(EntryStatus status) {
		this.status = status;
	}

	public DDateTime getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(DDateTime openingTime) {
		this.openingTime = openingTime;
	}

	public DDateTime getOpenTime() {
		return openTime;
	}

	public void setOpenTime(DDateTime openTime) {
		this.openTime = openTime;
	}

	public DDateTime getCancellingTime() {
		return cancellingTime;
	}

	public void setCancellingTime(DDateTime cancellingTime) {
		this.cancellingTime = cancellingTime;
	}

	public DDateTime getCancelledTime() {
		return cancelledTime;
	}

	public void setCancelledTime(DDateTime cancelledTime) {
		this.cancelledTime = cancelledTime;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public int getAllocatedSize() {
		return allocatedSize;
	}

	public void setAllocatedSize(int allocatedSize) {
		this.allocatedSize = allocatedSize;
	}

	public int getFilledSize() {
		return filledSize;
	}

	public void setFilledSize(int filledSize) {
		this.filledSize = filledSize;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public EntryType getType() {
		return type;
	}

	public void setType(EntryType type) {
		this.type = type;
	}

	public double getAvgFillPrice() {
		return avgFillPrice;
	}

	public void setAvgFillPrice(double avgFillPrice) {
		this.avgFillPrice = avgFillPrice;
	} 
	
	
	
	
}
