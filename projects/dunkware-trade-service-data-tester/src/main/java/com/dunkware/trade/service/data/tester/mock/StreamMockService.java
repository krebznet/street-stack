package com.dunkware.trade.service.data.tester.mock;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.anot.ADunkNetService;
import com.dunkware.stream.cluster.proto.controller.GetStreamDescriptors;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptors;
import com.google.protobuf.Descriptors.Descriptor;

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
	public StreamDescriptors getStreamDescriptors(GetStreamDescriptors get) {
		StreamDescriptors descriptors = new StreamDescriptors();
		StreamDescriptor descriptor = new StreamDescriptor();
		descriptor.setKafkaBrokers("localhost:29092");
		
		descriptor.setCoreDatabaseURL("mongodb://localhost:27017");
		descriptor.setCoreDatabase("dunklight");
		descriptor.setWarehouseDatabase("dunkhouse");
		descriptor.setWarehouseURL(descriptor.getCoreDatabaseURL());
		descriptor.setId(3);
		descriptor.setIdentifier("test");
		descriptor.setTimeZone(DTimeZone.NewYork);
		
		descriptors.getDescriptors().add(descriptor);
		return descriptors;

		
	
	}
	
}
