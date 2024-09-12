package com.dunkware.trade.tick.provider.polygon.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PolygonEvent {

	@JsonProperty(value = "ev")
	private String event; 
	@JsonProperty(value = "sym")
	private String symbol; 
	@JsonProperty(value = "bx")
	private int bidExchangeId;
	@JsonProperty(value = "bp")
	private double bidPrice;
	@JsonProperty(value = "bs")
	private int bidSize; 
	@JsonProperty(value = "ax")
	private int askExchangeid;
	@JsonProperty(value = "ap")
	private double askPrice;
	@JsonProperty(value = "as")
	private int askSize; 
	@JsonProperty(value = "c")
	private int condition; 
	@JsonProperty(value = "t")
	private long timestamp;
	@JsonProperty(value = "z")
	private int tape; 
	@JsonProperty(value = "v")
	private int tickVolume;
	@JsonProperty(value = "av")
	private int volume; 
	@JsonProperty(value = "op")
	private double openPrice;
	@JsonProperty(value = "vw")
	private double tickVwap; 
	@JsonProperty(value = "o")
	private double tickOpen; 
	@JsonProperty(value = "o")
	private double tickClose; 
	private double tickHigh; 
	private double tickLow; 
	
}
