package com.dunkware.trade.assets.core;

import java.util.List;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.events.DEventTree;
import com.dunkware.trade.assets.type.AssetTypeHandler;

import reactor.core.Exceptions;

public interface AssetService {

	/**
	 * Inserts an asset 
	 * @param type
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public Asset insertAsset(String json, String type) throws Exception;
	
	/**
	 * returns an asset based on the id 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Asset getAsset(long id) throws Exception; 
	
	/**
	 * Returns all assets for the given types 
	 * @param types
	 * @return
	 * @throws Exceptions
	 */
	public List<Asset> getAssets(String...types) throws Exception; 
	
	/**
	 * Returns all assets 
	 * @return
	 * @throws Exception
	 */
	public List<Asset> getAssets() throws Exception;
	
	
	public AssetTypeHandler getTypeHandler(String type) throws Exception;
	
	/**
	 * Returns event node
	 * @return
	 */
	public DEventNode getEventNode();
}
