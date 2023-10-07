package com.dunkware.trade.assets.core.impl;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.assets.core.Asset;
import com.dunkware.trade.assets.type.AssetTypeHandler;
import com.dunkware.trade.persistence.asset.AssetEntity;

public class AssetImmpl implements Asset {
	
	private AssetEntity entity; 
	private AssetTypeHandler typeHandler; 
	
	private AssetServiceImpl service; 
	
	private Object model;
	
	private DEventNode eventNode; 
	
	public void init(AssetEntity entity, AssetServiceImpl service, boolean newAsset) throws Exception { 
		this.entity = entity;
		this.service = service;
		this.eventNode = service.getEventNode().createChild(this);
		this.typeHandler = service.getTypeHandler(entity.getType());
		model = typeHandler.deserializeModel(entity.getJson());
		
		
	}
	
	

	@Override
	public long getId() {
		return entity.getId();
	}



	@Override
	public String getType() {
		return entity.getType();
	}



	@Override
	public Object getModel() {
		return model;
	}



	public AssetServiceImpl getService() {
		return service;
	}



	public void setService(AssetServiceImpl service) {
		this.service = service;
	}



	@Override
	public void save(String jsonModel) throws Exception {
		try {
			typeHandler.validateModel(jsonModel);;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public DEventNode getEventNode() {
		return eventNode;
	}

	@Override
	public AssetEntity getEntity() {
		return entity;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	
}
