package com.dunkware.trade.service.stream.server.blueprint;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.DunkNetChannelHandler;
import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.stream.cluster.proto.controller.blueprint.StreamBlueprintEntityDescriptor;
import com.dunkware.stream.cluster.proto.controller.blueprint.StreamBlueprintSignalDescriptor;
import com.dunkware.stream.cluster.proto.controller.blueprint.StreamBlueprintVarDescriptor;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.StreamControllerService;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class StreamBlueprintChannel implements DunkNetChannelHandler {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamBlueprintChannel");

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
		Thread wrapper = new Thread() { 
			
			public void run() { 
				
				try {
					Thread.sleep(4000);
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
					
					List<StreamBlueprintVarDescriptor> varDescriptors = new ArrayList<StreamBlueprintVarDescriptor>();
					for (StreamBlueprintVarBean varBean : blueprint.getVarBeans()) {
						StreamBlueprintVarDescriptor descriptor = new  StreamBlueprintVarDescriptor();
						descriptor.setGroup(varBean.getGroup());
						descriptor.setId((int)varBean.getId());
						descriptor.setName(varBean.getName());;
						descriptor.setIdent(varBean.getIdentifier());
						varDescriptors.add(descriptor);
					}
					
					for (StreamBlueprintVarDescriptor streamBlueprintVarDescriptor : varDescriptors) {
						channel.event(streamBlueprintVarDescriptor);
					}
					
				} catch (Exception e) {
					logger.error(marker, "Exception starting or sending messsage events {}",e.toString(),e	);
				}
				
				
			}
			
		}; 
		
		wrapper.start();
		
		
		
		
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
