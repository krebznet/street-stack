package com.dunkware.trade.service.stream.json.blueprint;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.utils.core.json.DunkJson;
import com.dunkware.xstream.model.entity.query.type.StreamEntityCriteriaType;
import com.dunkware.xstream.model.signal.type.StreamSignalType;

public class WebStreamEntitySignalType {

	public static final String JSON = "{\"id\":52,\"name\":\"Acid Test\",\"description\":\"HELLO\",\"enableSymbolThrottle\":false,\"symbolThrottle\":0,\"enableSymbolLimit\":false,\"symbolLimit\":0,\"activeTimeWindow\":false,\"afterTime\":\"\",\"beforeTime\":\"\",\"criterias\":[{\"id\":7,\"type\":\"Value Filter\",\"compareFunction\":\"\",\"condition\":\"Greater Than\",\"conditionValue\":6,\"value\":[{\"valueType\":\"Field Current Value\",\"identifier\":\"TickLast1sec\",\"timeRange\":\"\",\"time\":\"\",\"timeUnit\":\"\",\"startTime\":\"\",\"stopTime\":\"\",\"sessionAggregation\":\"\"},{\"valueType\":\"\",\"identifier\":\"\",\"timeRange\":\"\",\"time\":0,\"timeUnit\":\"\",\"startTime\":\"\",\"stopTime\":\"\",\"sessionAggregation\":\"\"}]}]}\n"
			+ "";
	
	private long id; 
	private List<StreamEntityCriteriaType> criterias = new ArrayList<StreamEntityCriteriaType>();
	private String name; 
	private boolean enableSymbolThrottle; 
	private int symbolThrottle; 
	private boolean enableSymbolLimit; 
	private int symbolLimit;
	private String description; 
	private String startTime; 
	private String stopTime; 
	private boolean enableTimeWindow;
	
	public static void main(String[] args) {
		try {
			StreamSignalType t = DunkJson.getObjectMapper().readValue(JSON, StreamSignalType.class);
			System.out.println(t.getCriterias().get(0).getType());
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<StreamEntityCriteriaType> getCriterias() {
		return criterias;
	}
	public void setCriterias(List<StreamEntityCriteriaType> criterias) {
		this.criterias = criterias;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isEnableSymbolThrottle() {
		return enableSymbolThrottle;
	}
	public void setEnableSymbolThrottle(boolean enableSymbolThrottle) {
		this.enableSymbolThrottle = enableSymbolThrottle;
	}
	public int getSymbolThrottle() {
		return symbolThrottle;
	}
	public void setSymbolThrottle(int symbolThrottle) {
		this.symbolThrottle = symbolThrottle;
	}
	public boolean isEnableSymbolLimit() {
		return enableSymbolLimit;
	}
	public void setEnableSymbolLimit(boolean enableSymbolLimit) {
		this.enableSymbolLimit = enableSymbolLimit;
	}
	public int getSymbolLimit() {
		return symbolLimit;
	}
	public void setSymbolLimit(int symbolLimit) {
		this.symbolLimit = symbolLimit;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getStopTime() {
		return stopTime;
	}
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
	public boolean isEnableTimeWindow() {
		return enableTimeWindow;
	}
	public void setEnableTimeWindow(boolean enableTimeWindow) {
		this.enableTimeWindow = enableTimeWindow;
	}
	
	
	
}
