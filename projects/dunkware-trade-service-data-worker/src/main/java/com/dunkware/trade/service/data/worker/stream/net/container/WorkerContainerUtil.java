package com.dunkware.trade.service.data.worker.stream.net.container;

import java.util.List;

import com.dunkware.net.proto.data.cluster.GContainerEntity;
import com.dunkware.net.proto.data.cluster.GContainerServerMessage;
import com.dunkware.net.proto.data.cluster.GEntitySearchComplete;
import com.dunkware.net.proto.data.cluster.GEntitySearchException;
import com.dunkware.net.proto.data.cluster.GEntitySearchResults;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerException;

public class WorkerContainerUtil {

	public static GContainerServerMessage createSearchExceptionMessage(String exception, String workerId) { 
		GEntitySearchException exp = GEntitySearchException.newBuilder().setException(exception).setWorkerId(workerId).build();
		return GContainerServerMessage.newBuilder().setEntitySearchException(exp).build();
	}
	
	public static GContainerEntity createProtocolEntity(ContainerEntity entity) throws ContainerException { 
		GContainerEntity.Builder builder =  GContainerEntity.newBuilder();
		builder.setEntId(entity.getId());
		builder.setEntIdent(entity.getIdent());
		try {
			builder.setVars(entity.getCurrentVarValuesJson());
		} catch (Exception e) {
			throw new ContainerException("Exception getting enetity current values as json string " + e.toString());
			
		}
		return builder.build();
		
		
	}
	
	public static GContainerServerMessage buildSearchResults(List<GContainerEntity> ents, int searchId, String workerId) { 
		return GContainerServerMessage.newBuilder().setEntitySearchResults(GEntitySearchResults.newBuilder().addAllEntities(ents).setSearchId(searchId).setWorkerId(workerId).build()).build();
		
	}
	
	public static GContainerServerMessage buildEntitySearchComplete(int searchId, String workerId) { 
		return GContainerServerMessage.newBuilder().setEntitySearchComplete(GEntitySearchComplete.newBuilder().setSearchId(searchId).setWorkerId(workerId).build()).build();
		
	}
}
