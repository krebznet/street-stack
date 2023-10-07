package com.dunkware.trade.assets.core;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.persistence.asset.AssetEntity;

/**
 * A Trade Asset is has a JSON based model associated wit it as well as a asset type
 * for each assset type we have a registered asset type handler in the type package that
 * serializes/deserializes/validates the asset model. the asset service works with one table
 * and has methods to get assets by types and other query params. So Assets are generic, they
 * can be any kind of model based entity. Typically ussed when defining things inside the web
 * app like a trade play, or a broker, or a system, etc. 
 *
 */
public interface Asset {
	
	public Object getModel();
	
	public long getId();
	
	public String getType();
	
	public void save(String jsonModel) throws Exception;
	
	public DEventNode getEventNode();
	
	public AssetEntity getEntity();
	
	public void delete();
	
	
	

}
