package com.dunkware.trade.service.data.model.signals.bean;

import java.time.LocalDateTime;

import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.observable.ObservableBean;
import com.dunkware.common.util.time.DunkTime;

public class StreamSignalBean extends ObservableBean {
	
	private int rowId = DRandom.getRandom(0, 3939493);
	private int signalId; 
	private String signalName; 
	private String signalGroup; 
	private int entityId; 
	private String entityIdentifier; 
	private String entityName; 
	private double signalPrice; 
	private LocalDateTime dateTime;
	
	
	public static void main(String[] args) {
		StreamSignalBean b = new StreamSignalBean();
		b.setSignalGroup("Acid Tests");
		b.setSignalId(1);;
		b.setEntityId(3);
		b.setSignalName("Acid Test 1");
		b.setEntityIdentifier("AAPL");
		b.setEntityName("Apple Corporation");
		
		try {
			String serialized = DJson.serializePretty(b);
			System.out.println(serialized);
			StreamSignalBean bb = DJson.getObjectMapper().readValue(serialized, StreamSignalBean.class);
			System.out.println(bb.getSignalGroup());
		} catch (Exception e) {
			e.printStackTrace();
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
	public int getEntityId() {
		return entityId;
	}
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	public String getEntityIdentifier() {
		return entityIdentifier;
	}
	public void setEntityIdentifier(String entityIdentifier) {
		this.entityIdentifier = entityIdentifier;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public double getSignalPrice() {
		return signalPrice;
	}
	public void setSignalPrice(double signalPrice) {
		this.signalPrice = signalPrice;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}


	public int getRowId() {
		return rowId;
	}


	public void setRowId(int rowId) {
		this.rowId = rowId;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj instanceof StreamSignalBean) { 
			StreamSignalBean bean = (StreamSignalBean)obj;
			if(bean.getRowId() == getRowId()) { 
				return true;
			}
			
		}
		return false; 
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("sign:");
		builder.append(signalId);
		builder.append("entity:");
		builder.append(entityId);
		builder.append("timestamp:");
		builder.append(DunkTime.format(getDateTime(), DunkTime.YYYY_MM_DD_HH_MM_SS));
		return builder.toString();
	} 
	
	
	
	
	
	
	
	
	

}
