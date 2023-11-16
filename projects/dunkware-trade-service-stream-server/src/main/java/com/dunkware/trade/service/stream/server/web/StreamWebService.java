package com.dunkware.trade.service.stream.server.web;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeBean;
import com.dunkware.trade.service.stream.server.web.components.EntitySessionVarGrid;
import com.dunkware.xstream.model.entity.StreamEntitySnapshot;

import ca.odell.glazedlists.ObservableElementList;

public interface StreamWebService {

	
	public ObservableElementList<StreamSessionNodeBean> getStreamNodes(String streamIdentifier) throws Exception; 
	
	public DExecutor getExecutor();
	
	public EntitySessionVarGrid getEntitySessionVarGrid(int entityId) throws Exception; 
	
	public StreamEntitySnapshot getEntitySnapshot(String stream, String entityIdent) throws Exception;
	
	
}
