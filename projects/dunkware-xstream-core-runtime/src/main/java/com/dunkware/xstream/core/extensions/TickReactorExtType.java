package com.dunkware.xstream.core.extensions;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.tick.reactor.blueprint.ReactorBlueprint;
import com.dunkware.xstream.xproject.model.XStreamExtensionType;
import com.fasterxml.jackson.annotation.JsonProperty;


public class TickReactorExtType extends XStreamExtensionType {
	
	private ReactorBlueprint blueprint;
	private int tps;
	@JsonProperty(value = "dataticks")
	private List<TickReactorExtTypeDataTick> dataTicks = new ArrayList<TickReactorExtTypeDataTick>();
	
	
	public TickReactorExtType() {
		
	}
	
	public ReactorBlueprint getBlueprint() {
		return blueprint;
	}

	public void setBlueprint(ReactorBlueprint blueprint) {
		this.blueprint = blueprint;
	}

	public int getTps() {
		return tps;
	}

	public void setTps(int tps) {
		this.tps = tps;
	}

	public List<TickReactorExtTypeDataTick> getDataTicks() {
		return dataTicks;
	}

	public void setDataTicks(List<TickReactorExtTypeDataTick> dataTicks) {
		this.dataTicks = dataTicks;
	}
	
	
	
	
	

	
	
	
	

}
