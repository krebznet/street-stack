package com.dunkware.trade.service.stream.server.web.components;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.common.util.glazed.GlazedDataGrid;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.StreamControllerService;
import com.dunkware.trade.service.stream.server.controller.session.StreamSession;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionStarted;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionStopping;

import ca.odell.glazedlists.ObservableElementList;

public class EntitySessionVarGrid {

	private int entityId; 
	
	private ObservableElementList<EntitySessionVarBean> beans;
	
	private GlazedDataGrid dataGrid;
	
		
	@Autowired
	private StreamControllerService streamService; 
	
	private StreamController streamController; 
	

	
	private StreamSession straeamSession;

	

	
	
	@ADEventMethod
	public void streamSessionStarted(EStreamSessionStarted started) { 
		// 
	}
	
	
	@ADEventMethod
	public void streamSessionStopping(EStreamSessionStopping stopping) { 
		
	}
	
	
	
}
