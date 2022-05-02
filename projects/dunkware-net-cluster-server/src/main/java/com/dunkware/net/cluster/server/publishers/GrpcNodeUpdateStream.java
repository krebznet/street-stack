package com.dunkware.net.cluster.server.publishers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.json.DJson;
import com.dunkware.net.cluster.server.core.ClusterNode;
import com.dunkware.net.cluster.server.core.ClusterService;
import com.dunkware.net.proto.cluster.GNodeUpdate;
import com.dunkware.net.proto.cluster.GNodeUpdateRequest;
import com.dunkware.net.proto.cluster.GNodeUpdateResponse;

import io.grpc.stub.StreamObserver;

public class GrpcNodeUpdateStream {
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ClusterService clusterService; 
	private GNodeUpdateRequest request; 
	private StreamObserver<GNodeUpdateResponse> responseObserver;
	
	private ClusterNode node; 
	
	private NodeStatsPublisher publisher;
	
	public GrpcNodeUpdateStream(ClusterService clusterService, GNodeUpdateRequest request, StreamObserver<GNodeUpdateResponse> responseObserver) throws Exception { 
		this.clusterService = clusterService;
		this.request = request;
		this.responseObserver = responseObserver;
		try {
			clusterService.getNodeService().getNode(request.getNode());
		} catch (Exception e) {
			logger.error("Exception creating GrpcNodeUpdate Stream " + e.toString());
			responseObserver.onError(e);
			return;
		}
		publisher = new NodeStatsPublisher();
		publisher.start();
		
	}
	
	public void stop() { 
		responseObserver.onCompleted();
		publisher.interrupt();
	}
	
	private class NodeStatsPublisher extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				try {
					Thread.sleep(1000);	
				} catch (Exception e) {
					// okay interruped
				}
				
				List<ClusterNode> updateNodes = new ArrayList<ClusterNode>();
				Collection<ClusterNode> nodes = clusterService.getNodeService().getNodes();
				for (ClusterNode node : nodes) {
					if(node.getId().equals(request.getNode()) == false) { 
						updateNodes.add(node);
					}
				}
				GNodeUpdateResponse.Builder builder = GNodeUpdateResponse.newBuilder();
				for (ClusterNode clusterNode : updateNodes) {
					GNodeUpdate.Builder updateBuilder = GNodeUpdate.newBuilder();
					try {
						updateBuilder.setNode(clusterNode.getId());
						String stats = DJson.serializePretty(clusterNode.createUpdate());
						updateBuilder.setJson(stats);
						builder.addUpdates(updateBuilder.build());
					} catch (Exception e) {
						logger.error("Exception publishing node stats " + e.toString(),e);
						continue;
					}	
				}
				if(logger.isTraceEnabled()) { 
					logger.trace("Publishing Node Stat Count {} to Node {}", updateNodes.size(),request.getNode());
				}
				GNodeUpdateResponse resp = builder.build();
				try {
					responseObserver.onNext(resp);	
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
			}
		}
	}
}
