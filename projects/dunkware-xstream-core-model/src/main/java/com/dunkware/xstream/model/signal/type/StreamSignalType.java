package com.dunkware.xstream.model.signal.type;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.utils.core.json.DunkJson;
import com.dunkware.xstream.model.entity.query.type.StreamEntityCriteriaType;

public class StreamSignalType {

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

	String incorrectJson = "{id: 267107086801, productCode: 02-671070868,"
			+ " lastUpdate: 2018-07-15, lastUpdateTimestamp: 2018-07-15 01:49:58,"
			+ " user: {pf: {document: 123456789, name: Luis Fernando},"
			+ " address: {street: Rua Pref. Josu00e9 Alves Lima,number:37},"
			+ " payment: [{sequential: 1, id: CREDIT_CARD, value: 188, installments: 9}]}";

	public static void main(String[] args) {
		try {
			String json = com.dunkware.utils.core.helpers.DunkFile.readFileContents(new File(
					"/Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-stream-json/src/main/java/com/dunkware/trade/service/stream/json/blueprint/FileFuck.json"));

			StreamSignalType type = DunkJson.getObjectMapper().readValue(json, StreamSignalType.class);
			type.getName();
			System.out.println(type.toString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	public List<StreamEntityCriteriaType> getCriterias() {
		return criterias;
	}

	public void setCriterias(List<StreamEntityCriteriaType> criterias) {
		this.criterias = criterias;
	}

	public String getIncorrectJson() {
		return incorrectJson;
	}

	public void setIncorrectJson(String incorrectJson) {
		this.incorrectJson = incorrectJson;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
