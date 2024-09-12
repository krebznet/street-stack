package com.dunkware.trade.tick.provider.polygon.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PolygonLastTrade {

	@JsonProperty(value = "c")
	private int[] codes;
	@JsonProperty(value = "P")
	private double price;
	@JsonProperty(value = "s")
	private int size; 
	
	public int[] getCodes() {
		return codes;
	}

	public void setCodes(int[] codes) {
		this.codes = codes;
	}

	
}
