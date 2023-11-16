
package com.dunkware.trade.service.stream.server.web.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.spring.runtime.services.ExecutorService;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeBean;
import com.dunkware.trade.service.stream.json.controller.spec.StreamState;
import com.dunkware.trade.service.stream.server.blueprint.StreamBlueprint;
import com.dunkware.trade.service.stream.server.blueprint.StreamBlueprintService;
import com.dunkware.trade.service.stream.server.blueprint.StreamBlueprintVarBean;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.StreamControllerService;
import com.dunkware.trade.service.stream.server.controller.session.StreamSession;
import com.dunkware.trade.service.stream.server.web.StreamWebService;
import com.dunkware.trade.service.stream.server.web.components.EntitySessionVarGrid;
import com.dunkware.xstream.model.entity.StreamEntitySnapshot;
import com.dunkware.xstream.model.entity.StreamEntitySnapshotVar;

import ca.odell.glazedlists.ObservableElementList;

@Service
public class StreamWebServiceImpl implements StreamWebService  {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamWebService");

	@Autowired
	private StreamControllerService streamService; 
	
	@Autowired
	private ExecutorService executorService; 
	
	@Autowired
	private StreamBlueprintService blueprintService; 
	
	@Override
	public ObservableElementList<StreamSessionNodeBean> getStreamNodes(String streamIdentifier) throws Exception {
		ObservableElementList<StreamSessionNodeBean> elements = streamService.getStreamByName(streamIdentifier).getSessionNodeBeans();
		return elements;
	}
	
	public DExecutor getExecutor() { 
		return executorService.get();
	}

	@Override
	public EntitySessionVarGrid getEntitySessionVarGrid(int entityId) throws Exception {
		return  null;
	}

	@Override
	public StreamEntitySnapshot getEntitySnapshot(String stream, String entityIdent) throws Exception {
		StreamController streamController = streamService.getStreamByName(stream);
		int entityId = streamController.getTickerId(entityIdent);
		if(streamController.getState() == StreamState.Running) { 
			StreamSession session = streamController.getSession();;
			return session.entitySnapshot(entityId);
		} else { 
			StreamBlueprint blueprint = blueprintService.getBlueprint(stream);
			StreamEntitySnapshot snapshot = new StreamEntitySnapshot();
			snapshot.setDateTime(LocalDateTime.now(DTimeZone.toZoneId(streamController.getTimeZone())));
			snapshot.setId(entityId);
			snapshot.setIdentifier(entityIdent);
			try {
				blueprint.getVarBeans().getReadWriteLock().readLock().lock();
				for (StreamBlueprintVarBean varBean : blueprint.getVarBeans()) {
					StreamEntitySnapshotVar snapVar = new StreamEntitySnapshotVar();
					snapVar.setId((int)varBean.getId());
					snapVar.setIdentifier(varBean.getIdentifier());
					snapVar.setUpdateCount(0);
					snapshot.getVars().put((int)varBean.getId(), snapVar);
				}
				return snapshot; 
			} catch (Exception e) {
				logger.error(marker, "Exception getting entity snapshot " + e.toString());
				throw e;
			} finally { 
				blueprint.getVarBeans().getReadWriteLock().readLock().unlock();
			}
		}
		
		
	}
	

	

	
}
