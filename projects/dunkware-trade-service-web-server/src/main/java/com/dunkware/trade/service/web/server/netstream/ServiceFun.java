package com.dunkware.trade.service.web.server.netstream;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.core.anot.ANetCallService;
import com.dunkware.net.core.service.NetCallRequest;
import com.dunkware.net.core.service.NetCallResponse;
import com.dunkware.net.core.service.NetCallService;
import com.dunkware.net.core.service.NetServiceException;

@ANetCallService(endpoint = "/service/list")
public class ServiceFun implements NetCallService {

	@Autowired
	private Cluster cluster;
	
	@Override
	public void service(NetCallRequest req, NetCallResponse resp) throws NetServiceException {
		// TODO Auto-generated method stub
		// NetList of things -> 
		
		// StreamSource 
			// Variable -> id name, label, 
			// SignalType 
		
			// Stream Var -> name --. id, name, variable, version 
			// CellEditor Variable -> stream /sdf/ = display("dd") apply("dd") 
		
		
		// ServiceBean 
		
			
			//@Field(name, type, required)

			//@Field(name,Type,required); 
			// then you can conver them to GBean 
		
			// could you reference another bean type - 
		
			//@Field(bean -ref ="/StreamSpec")
	}
	
	

}
