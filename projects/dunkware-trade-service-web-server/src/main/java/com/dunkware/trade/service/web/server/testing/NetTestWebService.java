package com.dunkware.trade.service.web.server.testing;

import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.core.data.GBeanReader;
import com.dunkware.net.core.service.NetCallResponseCallback;
import com.dunkware.net.core.util.GNetFactory;
import com.dunkware.net.proto.net.GNetCallResponse;
import com.dunkware.net.proto.net.GNetMessage;

@Controller
public class NetTestWebService {

	@Autowired
	private Cluster cluster; 
	
	@GetMapping(path = "/feed/provider/stats")
	public @ResponseBody String feedProviderStats() {
		AtomicBoolean completd = new AtomicBoolean(false);
		StringBuilder builder = new StringBuilder();
		
		GNetMessage req = GNetFactory.callRequest("/feed/provider/stats",5,cluster.getNodeId());
		
		try {
			cluster.netCall("/feed/provider/stats", 5, new NetCallResponseCallback() {

				@Override
				public void onSuccess(GNetCallResponse response) {
					try {
						String name = GBeanReader.newInstance(response.getData()).getString("name");
						builder.append(name);
						completd.set(true);	
					} catch (Exception e) {
						completd.set(true);
						builder.append(e.toString());

					}
					
				}

				@Override
				public void onError(GNetCallResponse response) {
					builder.append(response.getException());
					completd.set(true);
				}
				
				
			});		
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
		
		while(completd.get() == false) { 
			try {
			
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		
		return builder.toString();
		
	}
}
