package com.dunkware.trade.service.stream.serverd.server.web;

import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeBean;
import com.dunkware.trade.service.stream.serverd.web.components.EntitySessionVarGrid;
import com.dunkware.utils.core.concurrent.DunkExecutor;

import ca.odell.glazedlists.ObservableElementList;

public interface StreamWebService {

	
	public ObservableElementList<StreamSessionNodeBean> getStreamNodes(String streamIdentifier) throws Exception; 
	
	public DunkExecutor getExecutor();
	
	public EntitySessionVarGrid getEntitySessionVarGrid(int entityId) throws Exception; 
	

	
}
