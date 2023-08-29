package com.dunkware.trade.service.data.tester.mock;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.anot.ADunkNetService;
import com.dunkware.trade.service.data.model.cluster.StreamDescriptorsReq;
import com.dunkware.trade.service.data.model.cluster.StreamDescriptorsResp;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;

@Service
public class StreamMockService {

	
	@Autowired
	private DunkNet dunkNet;
	
	
	@PostConstruct
	public void postInit() { 
		System.out.println("hello");
		try {
			dunkNet.extensions().addExtension(this);			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	@ADunkNetService(label = "Stream Descriptors Service")
	public StreamDescriptorsResp getStreamDescriptors(StreamDescriptorsReq req) {
		StreamDescriptor descriptor = new StreamDescriptor();
		descriptor.setEntityCount(4);
		descriptor.setKafkaBrokers("localhost:29092");
		descriptor.setSignalTopic("test_signal");
		descriptor.setMongoURL("mongodb://localhost:27017");
		descriptor.setMongoDatabase("dunkstreet");
		descriptor.setIdentifier("test");
		descriptor.setSignalTopic("test_topic");
		descriptor.setTimeZone(DTimeZone.NewYork);
		
		StreamDescriptorsResp resp = new StreamDescriptorsResp();
		resp.setDescriptors(Arrays.asList(descriptor));
		return resp;

		
	
	}
	
}
