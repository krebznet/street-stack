package com.dunkware.trade.service.data.model.signals.bean;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.observable.ObservableBean;

public class StreamSignalTypeBean extends ObservableBean {

	private int id = DRandom.getRandom(1, 434343434);
	private int signalId; 
	private String signalName; 
	private String signalGroup; 
	private int signalCount; 
	private LocalDateTime lastSignalTime;
	private int lastEntityId; 
	private String lastEntittyName; 
	private String lastEntityIdentifier;
	
	public static void main(String[] args) {
		StreamSignalTypeBean bean = new StreamSignalTypeBean();
		bean.setSignalId(3);
		bean.getId();
		bean.setSignalName("Breakout 1");
		bean.setSignalGroup("Breakotus");
		bean.setSignalCount(423);
		bean.setLastSignalTime(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
		bean.setLastEntittyName("Apple Corporation");
		bean.setLastEntityIdentifier("AAPL");
		bean.setLastEntityId(3);
		try {
			System.out.println(DJson.serializePretty(bean));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public int getSignalId() {
		return signalId;
	}
	public void setSignalId(int signalId) {
		this.signalId = signalId;
	}
	public String getSignalName() {
		return signalName;
	}
	public void setSignalName(String signalName) {
		this.signalName = signalName;
	}
	public String getSignalGroup() {
		return signalGroup;
	}
	public void setSignalGroup(String signalGroup) {
		this.signalGroup = signalGroup;
	}
	public int getSignalCount() {
		return signalCount;
	}
	public void setSignalCount(int signalCount) {
		this.signalCount = signalCount;
	}
	
	
	public LocalDateTime getLastSignalTime() {
		return lastSignalTime;
	}

	public void setLastSignalTime(LocalDateTime lastSignalTime) {
		this.lastSignalTime = lastSignalTime;
	}

	public int getLastEntityId() {
		return lastEntityId;
	}
	public void setLastEntityId(int lastEntityId) {
		this.lastEntityId = lastEntityId;
	}
	public String getLastEntittyName() {
		return lastEntittyName;
	}
	public void setLastEntittyName(String lastEntittyName) {
		this.lastEntittyName = lastEntittyName;
	}
	public String getLastEntityIdentifier() {
		return lastEntityIdentifier;
	}
	public void setLastEntityIdentifier(String lastEntityIdentifier) {
		this.lastEntityIdentifier = lastEntityIdentifier;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	
	
	
}
