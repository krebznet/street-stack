package com.dunkware.trade.assets.type;

import com.dunkware.trade.assets.core.Asset;

/**
 * For each asset type we will have a handler class that implements
 * this thing. right? 
 * @author duncankrebs
 *
 */
public interface AssetTypeHandler { 
	
	void validateModel(Object model) throws Exception; 
	
	String serializeModel(Object model) throws Exception; 
	
	Object deserializeModel(String json) throws Exception;
	
	void assetDeleted(Asset asset) throws Exception; 
	
	void assetLoaded(Asset asset) throws Exception;
	
	void assetCreated(Asset asset) throws Exception;
	
	void assetUpdated(Asset asset) throws Exception;
}