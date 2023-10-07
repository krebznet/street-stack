package com.dunkware.trade.assets.controllers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.assets.core.AssetService;

@RestController
public class AssetController {
	
	
	@Autowired
	private AssetService service; 
	
	/**
	 * Inserts an asset, we get the json string model that will be serialized/dersialized by 
	 * the asset type handler and we get the asset type to get the handler and store the record
	 * correctly. 
	 * @param json
	 * @param type
	 * @throws Exception
	 */
	@PostMapping(path = "trade/v1/assets/insert")
	public long insertAsset(@RequestBody() String json, @RequestParam() String type) throws Exception { 
		return service.insertAsset(json, type).getEntity().getId();
		
	}

}
