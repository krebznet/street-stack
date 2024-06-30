package com.dunkware.utils.tick.reactor.blueprint;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReactorBlueprint {
	
	@JsonProperty(value = "ticksets")
	private List<ReactorTickSet> ticksets = new ArrayList<ReactorTickSet>();
	@JsonProperty(value = "strategies")
	private List<ReactoryStrategy> strategies = new ArrayList<ReactoryStrategy>();
	
	public ReactorBlueprint() { 
		
	}
	
	public List<ReactorTickSet> getTicksets() {
		return ticksets;
	}

	public void setTicksets(List<ReactorTickSet> ticksets) {
		this.ticksets = ticksets;
	}

	public List<ReactoryStrategy> getStrategies() {
		return strategies;
	}

	public void setStrategies(List<ReactoryStrategy> strategies) {
		this.strategies = strategies;
	}
	
	


	
	
	

}
