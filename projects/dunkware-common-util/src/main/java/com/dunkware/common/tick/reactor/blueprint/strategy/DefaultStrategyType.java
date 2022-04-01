package com.dunkware.common.tick.reactor.blueprint.strategy;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.tick.reactor.blueprint.ReactorUpdater;
import com.dunkware.common.tick.reactor.blueprint.ReactoryStrategy;
import com.fasterxml.jackson.annotation.JsonProperty;



public class DefaultStrategyType extends ReactoryStrategy
{
	@JsonProperty(value = "updaters")
	private List<ReactorUpdater> updaters = new ArrayList<ReactorUpdater>();
	
	
	public DefaultStrategyType() { 
		
	}

	public List<ReactorUpdater> getUpdaters() {
		return updaters;
	}

	public void setUpdaters(List<ReactorUpdater> updaters) {
		this.updaters = updaters;
	}
	
	
	

	
	
	

}
