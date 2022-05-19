package com.dunkware.trade.service.data.service.stream.net.container;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.net.proto.netstream.GEntityMatcher;
import com.dunkware.net.proto.netstream.GNetScannerStopRequest;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamTimeUpdate;

public class DataStreamContainerWorker {

	private Map<String,Integer> entities = new ConcurrentHashMap<String,Integer>();
	private ClusterNode node; 
	private String workerId; 
	
	public void start(ClusterNode node, String workerId) { 
		// we need a GRPC server port i think; 
		// send request and tell it hey you are a node in this cluster
	}
	
	public void entityScannerRequest(GEntityMatcher matcher, String scannerIdentifier) { 
		
	}
	
	public void entityScannerStopRequest(GNetScannerStopRequest req) { 
		
	}
	
	public void sendSnapshot(GEntitySnapshot snapshot) { 
		
	}
	
	public void sendSignal(GEntitySignal signal) { 
		
	}
	
	public void sendTimeUpdate(GStreamTimeUpdate updaete) { 
		
	}
	
	// search results how the fuck do we do that? 
	
}