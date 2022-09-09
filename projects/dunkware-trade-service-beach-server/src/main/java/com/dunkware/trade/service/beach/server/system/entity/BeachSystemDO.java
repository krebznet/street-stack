package com.dunkware.trade.service.beach.server.system.entity;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dunkware.trade.service.beach.server.resources.entity.BeachResourceSystemDO;
import com.dunkware.trade.service.beach.server.trade.entity.BeachAccountDO;

@Entity(name = "BeachSystemDO")
@Table(name = "trade_system")
public class BeachSystemDO {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	private BeachResourceSystemDO systemResource;
	
	private LocalTime scheduleStartTime; 
	private LocalTime scheduleStopTime;  
	private String scheduleDays; 
	private boolean scheduleEnabled; 
	private BeachAccountDO account; 
	private double allocatedCapital;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public BeachResourceSystemDO getSystemResource() {
		return systemResource;
	}
	public void setSystemResource(BeachResourceSystemDO systemResource) {
		this.systemResource = systemResource;
	}
	public LocalTime getScheduleStartTime() {
		return scheduleStartTime;
	}
	public void setScheduleStartTime(LocalTime scheduleStartTime) {
		this.scheduleStartTime = scheduleStartTime;
	}
	public LocalTime getScheduleStopTime() {
		return scheduleStopTime;
	}
	public void setScheduleStopTime(LocalTime scheduleStopTime) {
		this.scheduleStopTime = scheduleStopTime;
	}
	public String getScheduleDays() {
		return scheduleDays;
	}
	public void setScheduleDays(String scheduleDays) {
		this.scheduleDays = scheduleDays;
	}
	public boolean isScheduleEnabled() {
		return scheduleEnabled;
	}
	public void setScheduleEnabled(boolean scheduleEnabled) {
		this.scheduleEnabled = scheduleEnabled;
	}
	public BeachAccountDO getAccount() {
		return account;
	}
	public void setAccount(BeachAccountDO account) {
		this.account = account;
	}
	public double getAllocatedCapital() {
		return allocatedCapital;
	}
	public void setAllocatedCapital(double allocatedCapital) {
		this.allocatedCapital = allocatedCapital;
	} 
	
	
	
	
}
