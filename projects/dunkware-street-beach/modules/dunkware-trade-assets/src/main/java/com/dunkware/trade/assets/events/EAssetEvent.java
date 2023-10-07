package com.dunkware.trade.assets.events;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.events.DEventTree;
import com.dunkware.trade.assets.core.Asset;

public class EAssetEvent extends DEvent {

	private Asset asset;
	
	
	public EAssetEvent(Asset asset) {
		this.asset = asset;
	}
	
	public String getType() { 
		return asset.getType();
	}
	
	
	
	

	
	
}
