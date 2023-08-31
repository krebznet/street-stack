package com.dunkware.trade.service.stream.json.blueprint;

import java.io.File;

import com.dunkware.common.util.helpers.DFileHelper;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.stream.json.query.WebStreamQuery;

public class WebStreamSignaltype {
	
	private WebStreamQuery query; 
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
				String json = DFileHelper.readFileContents(new File("/Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-stream-json/src/main/java/com/dunkware/trade/service/stream/json/blueprint/FileFuck.json"));

				WebStreamSignaltype type = DJson.getObjectMapper().readValue(json, WebStreamSignaltype.class);
						type.getName();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		
		
	public WebStreamQuery getQuery() {
		return query;
	}
	public void setQuery(WebStreamQuery query) {
		this.query = query;
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
