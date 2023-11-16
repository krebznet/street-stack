package com.dunkware.trade.service.stream.server.web.components;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.glazed.GlazedDataGrid;
import com.dunkware.common.util.observable.ObservableBeanListConnector;
import com.dunkware.trade.service.stream.server.blueprint.StreamBlueprint;
import com.dunkware.trade.service.stream.server.blueprint.StreamBlueprintService;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.StreamControllerService;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;

public class EntitySessionVarGrid {

	private int entityId; 
	
	private ObservableElementList<EntitySessionVarBean> beans;
	
	private GlazedDataGrid dataGrid;
	
	@Autowired
	private StreamBlueprintService bluePrintService; 
	
	@Autowired
	private StreamControllerService streamService; 
	
	private StreamController streamController; 
	
	private StreamBlueprint streamBlueprint; 
	
	public void start(String streamIdent, int entityId) throws Exception {
		try {
			streamController = streamService.getStreamByName(streamIdent);
		} catch (Exception e) {
			throw new Exception("Stream Identifier not found " + streamIdent);
		}
		
		try {
			streamBlueprint = bluePrintService.getBlueprint(streamIdent);
			if(streamBlueprint == null) { 
				throw new Exception("Stream blueprint not found for stream " + streamIdent);
			}
		} catch (Exception e) {
			throw new Exception("Exception getting stream blueprint " + e.toString());
		}
		
		beans = new ObservableElementList<EntitySessionVarBean>(GlazedLists.threadSafeList(new BasicEventList<EntitySessionVarBean>()),
				new ObservableBeanListConnector<EntitySessionVarBean>());
		
		
		
	}
	
}
