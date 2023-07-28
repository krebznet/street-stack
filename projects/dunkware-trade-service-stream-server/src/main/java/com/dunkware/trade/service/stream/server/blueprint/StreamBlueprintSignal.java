package com.dunkware.trade.service.stream.server.blueprint;

import java.time.LocalDate;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.stream.json.blueprint.WebBlueprintSignal;
import com.dunkware.trade.service.stream.server.repository.StreamBlueprintSignalEntity;
import com.dunkware.trade.service.stream.server.repository.StreamBlueprintSignalStatus;

public class StreamBlueprintSignal {

	private StreamBlueprintSignalEntity entity; 
	
	private WebBlueprintSignal  model;
	
	private StreamBlueprint blueprint;
	
	public void init(StreamBlueprintSignalEntity entity, StreamBlueprint blueprint) throws Exception { 
		try {
			this.entity = entity;
			this.blueprint = blueprint;
			model = DJson.getObjectMapper().readValue(entity.getModel(), WebBlueprintSignal.class);
		} catch (Exception e) {
			throw new Exception("Web signal model deserialize exeception " + e.toString());
		}
		this.entity = entity;
	}
	
	
	public StreamBlueprintSignalEntity getEntity() { 
		return entity;
	}
	
	public StreamBlueprintSignalStatus getStatus() { 
		return entity.getStatus();
	}
	
	public StreamBlueprint getBlueprint() { 
		return blueprint;
	}
	
	public LocalDate getEffectiveDate() { 
		return entity.getEffective();
	}
	
	public WebBlueprintSignal getModel() { 
		return model;
	}
}
