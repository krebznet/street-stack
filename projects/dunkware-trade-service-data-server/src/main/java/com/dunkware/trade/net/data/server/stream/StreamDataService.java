package com.dunkware.trade.net.data.server.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.trade.service.data.model.domain.EntitySignal;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;
import com.mongodb.client.model.InsertOneModel;


/**
 * This is the service that will get a list of StreamDescriptors from the 
 * stream controller service, using DunkNet for mock up testing we haev he
 * mock service prive a fake or mocked stream descriptor. 
 * 
 * A Stream Descriptor has a kafka broker, sream identifier, 
 * the variable descriptors which tell what data type each variable 
 * 
 * For each StreamDescriptor we will create a StreamInstance which is specific
 * to only 1 stream in the cluster. 
 * 
 * @author duncankrebs
 *
 */
@Service
public class StreamDataService {
	
	
	@Autowired
	private ApplicationContext ac;
	
	private Map<Integer,StreamDataController> streams = new ConcurrentHashMap<Integer,StreamDataController>();
	
	private StreamDataConfig config; 

	//@Autowired
	//private DunkNet dunkNet;
	
	// notice for now we are doing this in postContract
	@PostConstruct()
	public void init() {
		StreamDescriptor descriptor = new StreamDescriptor();
		descriptor.setIdentifier("us_equity");
		descriptor.setId(1);
		StreamDataController streamData = new StreamDataController();
		ac.getAutowireCapableBeanFactory().autowireBean(streamData);
		streamData.init(descriptor);
		streams.put(descriptor.getId(), streamData);
		 
	}
	

	
	public StreamDataController getStreamData(int streamId) throws Exception { 
		StreamDataController data = streams.get(streamId);
		if(data == null) { 
			throw new Exception("Stream " + streamId + " not found");
		}
		return streams.get(streamId);
	}
	
	
	public void testInsert(EntitySignal signal, int count) {
		//private List<InsertOneModel<Document>> pendingWrites = new ArrayList<InsertOneModel<Document>>();
		
		
		
		
	}

}
