package com.dunkware.trade.service.stream.server.blueprint;

import java.time.LocalDate;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.trade.service.stream.server.repository.StreamBlueprintSignalEntity;
import com.dunkware.trade.service.stream.server.repository.StreamBlueprintSignalStatus;
import com.dunkware.xstream.model.signal.type.StreamSignalType;

public class StreamBlueprintSignal {

	private StreamBlueprintSignalEntity entity; 
	
	private StreamSignalType  model;
	
	private StreamBlueprint blueprint;
	
	private DEventNode eventNode;
	
	private StreamBlueprintSignalBean bean;
	
	public void init(StreamBlueprintSignalEntity entity, StreamBlueprint blueprint) throws Exception { 
		try {
			bean = new StreamBlueprintSignalBean();
			
			this.entity = entity;
			this.blueprint = blueprint;
			eventNode = blueprint.getEventNode().createChild(this);
			model = DJson.getObjectMapper().readValue(entity.getModel(), StreamSignalType.class);
		} catch (Exception e) {
			throw new Exception("Web signal model deserialize exeception " + e.toString());
		}
		bean.setCreated(DunkTime.format(entity.getCreated(), DunkTime.YYYY_MM_DD_HH_MM_SS));
		bean.setName(model.getName());
		if (model.getDescription() != null) {
			bean.setDescription(model.getDescription());
		} else { 
			bean.setDescription(model.getName());
		}

		bean.setGroup("Default");
		bean.setStatus("Active");
		bean.setId(entity.getId());
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
	
	public String getName() { 
		return model.getName();
	}
	
	public StreamSignalType getSignalType() { 
		return model;
	}
}
