package com.dunkware.trade.net.data.server.stream.signals;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.stream.cluster.proto.controller.blueprint.StreamBlueprintChannelClient;
import com.dunkware.stream.cluster.proto.controller.blueprint.StreamBlueprintChannelException;
import com.dunkware.stream.cluster.proto.controller.blueprint.StreamBlueprintEntityDescriptor;
import com.dunkware.stream.cluster.proto.controller.blueprint.StreamBlueprintSignalDescriptor;
import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;
import com.dunkware.xstream.model.signal.StreamEntitySignal;

public class StreamSignalHelper {
	
	private static Logger logger = LoggerFactory.getLogger(StreamSignalHelper.class);
	
	
	public static StreamSignalBean entitySignalToBean(StreamEntitySignal signal, StreamBlueprintChannelClient blueprint) throws StreamBlueprintChannelException { 
		try {
			StreamSignalBean bean = new StreamSignalBean();
			StreamBlueprintSignalDescriptor signalDescriptor = blueprint.getSignalDescriptor(signal.getId());
			StreamBlueprintEntityDescriptor entityDescriptor = blueprint.getEntityDescriptor(signal.getEntity());
			bean.setDateTime(signal.getDateTime());;
			bean.setEntityId(signal.getEntity());
			bean.setEntityIdentifier(entityDescriptor.getIdent());;
			bean.setEntityName(entityDescriptor.getName());
			bean.setSignalId(signalDescriptor.getId());
			bean.setSignalName(signalDescriptor.getName());
			Map<Integer,Number> varDoc = signal.getVars();;
			Set<Entry<Integer,Number>> entries = varDoc.entrySet();
			boolean priceSet = false; 
			for (Entry<Integer, Number> entry : entries) {
				Object key = entry.getKey();
				if (key instanceof Integer) {
					Integer intKey = (Integer) key;
					bean.setSignalPrice((Double)entry.getValue());
					break;
				} else { 
					String keyString = (String)key;
					if(keyString.equals("2")) { 
						bean.setSignalPrice(entry.getValue().doubleValue());
						priceSet = true; 
						break;
					}	
				}
				
			}
			if(!priceSet) { 
				bean.setSignalPrice(-1);
			}
			
			bean.setSignalGroup(signalDescriptor.getGroup());;
			return bean; 
		} catch (Exception e) {
			logger.error("Convering entity signal to signal bean lookup missing values " + e.toString());
			StreamSignalBean bean = new StreamSignalBean();
			bean.setDateTime(signal.getDateTime());;
			bean.setEntityId(signal.getEntity());
			bean.setSignalId(signal.getId());
			bean.setEntityIdentifier("ERROR");
			bean.setEntityName("ERROR");
			bean.setSignalName("ERROR");
			Map<Integer,Number> varDoc = signal.getVars();;
			boolean priceSet = false; 
			Set<Entry<Integer,Number>> entries = varDoc.entrySet();
			for (Entry<Integer, Number> entry : entries) {
				Object key = entry.getKey();
				if (key instanceof Integer) {
					Integer intKey = (Integer) key;
					bean.setSignalPrice((Double)entry.getValue());
					priceSet = true;
				} else { 
					String keyString = (String)key;
					if(keyString.equals("2")) { 
						bean.setSignalPrice(entry.getValue().doubleValue());
						priceSet = true; 
						break;
					}	
				}
				
				
			}
			if(!priceSet) { 
				bean.setSignalPrice(-1);
			}
			return bean;
		
		}
	}
	
	
	
	

}
