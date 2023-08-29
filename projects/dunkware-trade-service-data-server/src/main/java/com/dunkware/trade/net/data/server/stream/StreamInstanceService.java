package com.dunkware.trade.net.data.server.stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.trade.service.data.model.cluster.StreamDescriptorsReq;
import com.dunkware.trade.service.data.model.cluster.StreamDescriptorsResp;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;

@Service()
public class StreamInstanceService {
	

	@Autowired
	private DunkNet dunkNet;
	
	// creates stream services 
	@PostConstruct
	public void init() {
		try {
			
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			 StreamDescriptorsResp resp = (StreamDescriptorsResp)dunkNet.serviceBlocking(new StreamDescriptorsReq());
			 for (StreamDescriptor descriptor : resp.getDescriptors()) {
				System.out.println(descriptor.getKafkaBrokers());
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		 
	}

}
