package com.dunkware.trade.service.stream.serverd.server.web.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.spring.runtime.services.ExecutorService;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeBean;
import com.dunkware.trade.service.stream.serverd.controller.StreamControllerService;
import com.dunkware.trade.service.stream.serverd.web.StreamWebService;
import com.dunkware.trade.service.stream.serverd.web.components.EntitySessionVarGrid;
import com.dunkware.utils.core.concurrent.DunkExecutor;

import ca.odell.glazedlists.ObservableElementList;

@Service
public class StreamWebServiceImpl implements StreamWebService  {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamWebService");

	@Autowired
	private StreamControllerService streamService; 
	
	@Autowired
	private ExecutorService executorService; 
	
	@Override
	public ObservableElementList<StreamSessionNodeBean> getStreamNodes(String streamIdentifier) throws Exception {
		ObservableElementList<StreamSessionNodeBean> elements = streamService.getStreamByName(streamIdentifier).getSessionNodeBeans();
		return elements;
	}
	
	public DunkExecutor getExecutor() { 
		return executorService.get();
	}

	@Override
	public EntitySessionVarGrid getEntitySessionVarGrid(int entityId) throws Exception {
		
		
		
		return  null;
	}

	
}
