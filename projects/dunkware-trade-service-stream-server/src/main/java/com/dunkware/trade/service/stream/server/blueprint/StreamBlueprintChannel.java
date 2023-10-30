package com.dunkware.trade.service.stream.server.blueprint;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.DunkNetChannelHandler;
import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.stream.cluster.proto.controller.blueprint.StreamBlueprintEntityDescriptor;
import com.dunkware.stream.cluster.proto.controller.blueprint.StreamBlueprintSignalDescriptor;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.StreamControllerService;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class StreamBlueprintChannel implements DunkNetChannelHandler {

	private DunkNetChannel channel; 
	
	private StreamBlueprint blueprint;
	
	@Autowired
	private StreamControllerService streamService; 
	
	public StreamBlueprintChannel(StreamBlueprint blueprint) { 
		this.blueprint = blueprint; 
	}
	
	@Override
	public void channelInit(DunkNetChannel channel) throws DunkNetException {
		this.channel = channel; 
	}

	@Override
	public void channelStart() throws DunkNetException {
		List<StreamBlueprintSignalDescriptor> signalDescriptors = new ArrayList<StreamBlueprintSignalDescriptor>();
		try {
			blueprint.getSignalBeans().getReadWriteLock().readLock().lock();
			for (StreamBlueprintSignalBean signal : blueprint.getSignalBeans()) {
				StreamBlueprintSignalDescriptor desc = new StreamBlueprintSignalDescriptor();	
				desc.setId(((int)signal.getId()));
				desc.setGroup(signal.getGroup());
				desc.setName(signal.getName());
				signalDescriptors.add(desc);
				//desc.setGroup(signal.get);
			}	
			blueprint.getSignalBeans().getReadWriteLock().readLock().unlock();
		} catch (Exception e) {
			throw new DunkNetException("Exception sending a StreamBlueprint descriptor or getting the stream blueprint signals " + e.toString());
		}
		
		for (StreamBlueprintSignalDescriptor descriptor : signalDescriptors) {
			channel.event(descriptor);
		}
		List<StreamBlueprintEntityDescriptor> entityDescriptors = new ArrayList<StreamBlueprintEntityDescriptor>();
		StreamController stream = null;
		try {
			stream = streamService.getStreamByName(blueprint.getStreamIdentifier());
		} catch (Exception e) {
			throw new DunkNetException("Exception getting stream controller from strema blueprint stream entity " + e.toString());
		}
		for (TradeTickerSpec tickerSpec : stream.getTickers()) {
			StreamBlueprintEntityDescriptor desc = new  StreamBlueprintEntityDescriptor();
			desc.setId(tickerSpec.getId());;
			desc.setName(desc.getName());;
			desc.setIdent(tickerSpec.getSymbol());
			entityDescriptors.add(desc);
		}
		/// now send the entity descriptors 
		for (StreamBlueprintEntityDescriptor streamBlueprintEntityDescriptor : entityDescriptors) {
			channel.event(streamBlueprintEntityDescriptor);
		}
		// now we need the variable descriptors; 
		
		// for now whatever you hvave to do
		//for (/ streamBlueprintEntityDescriptor : entityDescriptors) {
			
		//}stream.getScriptProject().getStreamVars()
		
		
		
		
		// okay now what about entities 
		
		
		
	}

	@Override
	public void channelClose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void channelStartError(String exception) {
		// TODO Auto-generated method stub
		
	}
	
	

}
