package com.dunkware.stream.cluster.proto.controller.blueprint;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.DunkNetChannelHandler;
import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.spring.cluster.anot.ADunkNetEvent;

public class StreamBlueprintChannelClient implements DunkNetChannelHandler {

	private DunkNetChannel channel; 
	
	private Map<Integer,StreamBlueprintEntityDescriptor> entityDescriptors = new ConcurrentHashMap<Integer,StreamBlueprintEntityDescriptor>();
	private Map<Integer,StreamBlueprintSignalDescriptor> signalDescriptors = new ConcurrentHashMap<Integer, StreamBlueprintSignalDescriptor>();
	private Map<Integer,StreamBlueprintVarDescriptor> varDescriptors = new ConcurrentHashMap<Integer, StreamBlueprintVarDescriptor>();
	
	private String startError = null;
	
	public StreamBlueprintChannelClient() throws Exception { 
		
	}

	@Override
	public void channelInit(DunkNetChannel channel) throws DunkNetException {
		this.channel = channel;
		channel.getExtensions().addExtension(this);;
		
		
	}

	@Override
	public void channelStart() throws DunkNetException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void channelClose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void channelStartError(String exception) {
		this.startError = exception;
	}
	
	/**
	 * Consume the entity descriptor events. 
	 * @param descriptor
	 */
	@ADunkNetEvent()
	public void entityDescriptor(StreamBlueprintEntityDescriptor descriptor) { 
		entityDescriptors.put(descriptor.getId(), descriptor);
	}
	
	
	/**
	 * Consume the signal descriptors 
	 * @param descriptor
	 */
	@ADunkNetEvent() 
	public void signalDescriptor(StreamBlueprintSignalDescriptor descriptor) { 
		this.signalDescriptors.put(descriptor.getId(), descriptor);
		
	}
	
	
	@ADunkNetEvent()
	public void varDescriptor(StreamBlueprintVarDescriptor descriptor)  { 
		this.varDescriptors.put(descriptor.getId(), descriptor);
	}
	
	
	public StreamBlueprintSignalDescriptor getSignalDescriptor(int signalId) throws StreamBlueprintChannelException { 
		StreamBlueprintSignalDescriptor descriptor = signalDescriptors.get(signalId);
		if(descriptor == null) { 
			throw new StreamBlueprintChannelException("Signal Descriptor not found for signal id " + signalId);
		}
		return descriptor; 
	}
	
	public StreamBlueprintEntityDescriptor getEntityDescriptor(int entityId) throws StreamBlueprintChannelException { 
		StreamBlueprintEntityDescriptor descriptor = entityDescriptors.get(entityId);
		if(descriptor == null) { 
			throw new StreamBlueprintChannelException("entity Descriptor not found for entity id " + entityId);
		}
		return descriptor; 
	}
	
	
	public StreamBlueprintVarDescriptor getVarDescriptor(int varId) throws StreamBlueprintChannelException { 
		StreamBlueprintVarDescriptor descriptor = varDescriptors.get(varId);
		if(descriptor == null) { 
			throw new StreamBlueprintChannelException("var Descriptor not found for entity id " + varId);
		}
		return descriptor; 
	}
	
	
	
}
	
	
