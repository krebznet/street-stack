package com.dunkware.trade.service.stream.server.blueprint;

import java.time.LocalDate;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.stream.json.blueprint.WebStreamSignaltype;
import com.dunkware.trade.service.stream.server.repository.StreamBlueprintSignalEntity;
import com.dunkware.trade.service.stream.server.repository.StreamBlueprintSignalStatus;

public class StreamBlueprintSignal {

	private StreamBlueprintSignalEntity entity; 
	
	private WebStreamSignaltype  model;
	
	private StreamBlueprint blueprint;
	
	private DEventNode eventNode;
	
	private StreamBlueprintSignalBean bean;
	
	public void init(StreamBlueprintSignalEntity entity, StreamBlueprint blueprint) throws Exception { 
		try {
			bean = new StreamBlueprintSignalBean();
			
			this.entity = entity;
			this.blueprint = blueprint;
			eventNode = blueprint.getEventNode().createChild(this);
			model = DJson.getObjectMapper().readValue(entity.getModel(), WebStreamSignaltype.class);
		} catch (Exception e) {
			throw new Exception("Web signal model deserialize exeception " + e.toString());
		}
		bean.setName(model.getName());
		bean.setDescription(model.getDescription());
		bean.setId(entity.getId());
		bean.setStatus(entity.getStatus().name());
		
		this.entity = entity;
	}
	
	
	public StreamBlueprintSignalBean getBean() { 
		return bean;
	}
	
	public DEventNode getEventNode() {
		return eventNode;
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
	
	public WebStreamSignaltype getModel() { 
		return model;
	}
}
