package com.dunkware.trade.service.beach.model.system.signal;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SignalSystem {

	private int accountId; 
	private double allocatedCapital;
	private boolean enableSchedule;
	private LocalTime scheduleStopTime;
	private LocalTime scheduleStartTime;
	private List<String> scheduleDays = new ArrayList<String>();
	
	private List<SignalSystemTrade> trades = new ArrayList<SignalSystemTrade>();

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getAllocatedCapital() {
		return allocatedCapital;
	}

	public void setAllocatedCapital(double allocatedCapital) {
		this.allocatedCapital = allocatedCapital;
	}

	public LocalTime getScheduleStopTime() {
		return scheduleStopTime;
	}

	public void setScheduleStopTime(LocalTime scheduleStopTime) {
		this.scheduleStopTime = scheduleStopTime;
	}

	public LocalTime getScheduleStartTime() {
		return scheduleStartTime;
	}

	public void setScheduleStartTime(LocalTime scheduleStartTime) {
		this.scheduleStartTime = scheduleStartTime;
	}

	public List<String> getScheduleDays() {
		return scheduleDays;
	}

	public void setScheduleDays(List<String> scheduleDays) {
		this.scheduleDays = scheduleDays;
	}

	public List<SignalSystemTrade> getTrades() {
		return trades;
	}

	public void setTrades(List<SignalSystemTrade> trades) {
		this.trades = trades;
	}

	public boolean isEnableSchedule() {
		return enableSchedule;
	}

	public void setEnableSchedule(boolean enableSchedule) {
		this.enableSchedule = enableSchedule;
	}
	
	
	
	
	
}
