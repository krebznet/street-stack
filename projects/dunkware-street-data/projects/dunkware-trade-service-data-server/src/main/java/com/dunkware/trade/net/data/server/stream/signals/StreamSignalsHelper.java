package com.dunkware.trade.net.data.server.stream.signals;

import com.dunkware.stream.cluster.proto.controller.blueprint.StreamBlueprintChannelClient;
import com.dunkware.stream.cluster.proto.controller.blueprint.StreamBlueprintChannelException;
import com.dunkware.stream.cluster.proto.controller.blueprint.StreamBlueprintEntityDescriptor;
import com.dunkware.stream.cluster.proto.controller.blueprint.StreamBlueprintSignalDescriptor;
import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;
import com.dunkware.xstream.model.signal.StreamEntitySignal;

public class StreamSignalsHelper {
	
	
	public static StreamSignalBean entitySignalToBean(StreamEntitySignal signal, StreamBlueprintChannelClient blueprint) throws StreamBlueprintChannelException { 
		try {
			StreamSignalBean bean = new StreamSignalBean();
			StreamBlueprintSignalDescriptor signalDescriptor = blueprint.getSignalDescriptor(signal.getId());
			StreamBlueprintEntityDescriptor entityDescriptor = blueprint.getEntityDescriptor(signal.getEntity());
			bean.setDateTime(signal.getDateTime());;
			bean.setEntityId(signal.getEntity());
			bean.setEntityIdentifier(entityDescriptor.getIdent());;
			bean.setEntityName(entityDescriptor.getName());
			bean.setSignalName(signalDescriptor.getName());
			bean.setSignalPrice((Double)signal.getVars().get(2));
			bean.setSignalGroup(signalDescriptor.getGroup());;
			return bean; 
		} catch (Exception e) {
			throw new StreamBlueprintChannelException("Exception looking up stream blueprint descriptor data " + e.toString());
		}
	}
	
	
	
	

}
