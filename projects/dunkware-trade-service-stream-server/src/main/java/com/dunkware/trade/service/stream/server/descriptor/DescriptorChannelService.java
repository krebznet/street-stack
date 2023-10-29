package com.dunkware.trade.service.stream.server.descriptor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.DunkNetChannelHandler;
import com.dunkware.spring.cluster.anot.ADunkNetChannel;
import com.dunkware.stream.cluster.proto.controller.descriptor.DescriptorChannelRequest;

@Service
public class DescriptorChannelService {

	@Autowired
	private DunkNet dunkNet; 
	
	@PostConstruct
	private void init() { 
		
	}
	
	
	@ADunkNetChannel(label = "Stream Descritpro Channel")
	public DunkNetChannelHandler descriptorChannel(DescriptorChannelRequest request) {
		
		
		
	}
}
