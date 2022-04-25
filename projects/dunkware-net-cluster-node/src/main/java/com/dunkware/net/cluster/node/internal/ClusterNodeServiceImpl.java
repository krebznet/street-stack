package com.dunkware.net.cluster.node.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.json.DJson;
import com.dunkware.net.cluster.json.node.ClusterNodeState;
import com.dunkware.net.cluster.json.node.ClusterNodeType;
import com.dunkware.net.cluster.json.node.ClusterNodeUpdate;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.net.cluster.node.ClusterNodeException;
import com.dunkware.net.cluster.node.ClusterNodeService;
import com.dunkware.net.proto.cluster.GNodeUpdate;
import com.dunkware.net.proto.cluster.GNodeUpdateRequest;
import com.dunkware.net.proto.cluster.GNodeUpdateResponse;
import com.dunkware.net.proto.cluster.service.GClustererviceGrpc;
import com.dunkware.net.proto.cluster.service.GClustererviceGrpc.GClustererviceStub;

import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class ClusterNodeServiceImpl implements ClusterNodeService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private Map<String,ClusterNode> nodes = new ConcurrentHashMap<String,ClusterNode>();
	private ClusterImpl cluster;
	
	private GClustererviceStub stub;
	
	private BlockingQueue<GNodeUpdateResponse> responseQueue = new LinkedBlockingDeque<GNodeUpdateResponse>();
	
	NodeUpdaterHandler updateHandler; 
	
	public void start(ClusterImpl cluster) { 
		this.cluster = cluster;
		Thread runner = new Thread() { 
			
			public void run() { 
				try {
					Thread.sleep(5000);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					stub = GClustererviceGrpc.newStub(ManagedChannelBuilder.forTarget(cluster.getConfig().getClusterGrpc()).usePlaintext().build());
					GNodeUpdateRequest request = GNodeUpdateRequest.newBuilder().setNode(cluster.getNodeId()).build();
					
					StreamObserver<GNodeUpdateResponse> response = new StreamObserver<GNodeUpdateResponse>() {

						@Override
						public void onNext(GNodeUpdateResponse value) {
							if(logger.isDebugEnabled()) { 
								logger.debug(cluster.getNodeId() + " received node update response");
							}
							responseQueue.add(value);
						}

						@Override
						public void onError(Throwable t) {
							logger.error("Error returned on Node Update Request " + t.getMessage(),t);
							t.printStackTrace();
						}

						@Override
						public void onCompleted() {
							// TODO Auto-generated method stub
							
						}
					
					
					};
					
					stub.nodeUpdateStream(request, response);
					updateHandler = new NodeUpdaterHandler();
					updateHandler.start();
					
				} catch (Exception e) {
					logger.error(MarkerFactory.getMarker("Crash"),"Exception Start Cluster Node Service GRPC Update Stream Request " + e.toString());
					System.exit(-1);
				}
			}
		};
		runner.start();
		
	
	}

	@Override
	public List<ClusterNode> getAvailablWorkereNodes(int count) throws ClusterNodeException {
		List<ClusterNode> results = new ArrayList<ClusterNode>();
		int resultCount = 0;
		for (String key : nodes.keySet()) {
			ClusterNode node = nodes.get(key);
			if (node.getState() == ClusterNodeState.Available) {
				if (node.getType() == ClusterNodeType.Worker) {
					results.add(node);
					resultCount++;
					if (resultCount == count) {
						return results;
					}
				}
			}
		}
		throw new ClusterNodeException(
				"Requested " + count + " avaiable nodes is bigger than nodes avaiable which is " + results.size());
	}

	

	@Override
	public int getAvailableWorkerCount() {
		return getAvailableWorkerNodes().size();
	}

	@Override
	public List<ClusterNode> getAvailableWorkerNodes() {
		List<ClusterNode> results = new ArrayList<ClusterNode>();
		for (String key : nodes.keySet()) {
			ClusterNode node = nodes.get(key);
			if (node.getState() == ClusterNodeState.Available) {
				if (node.getType() == ClusterNodeType.Worker) {
					results.add(node);
				}
			}
		}
		return results;
	}
	

	private class NodeUpdaterHandler extends Thread { 
		
		public void run() { 
			while(!interrupted()) {
				GNodeUpdateResponse response = null;
				try {
					response = responseQueue.take();
					if(logger.isTraceEnabled()) { 
						logger.trace("Handling {} Node Updates on Node " + cluster.getNodeId(), response.getUpdatesList().size());
					}
				} catch (Exception e) {
					logger.error("Exception taking GNodeUpdateResponse from queue " + e.toString());
					continue;
				}
				for (GNodeUpdate gUpdate : response.getUpdatesList()) {
					ClusterNodeUpdate update = null;
					try {
						update = DJson.getObjectMapper().readValue(gUpdate.getJson(), ClusterNodeUpdate.class);
					} catch (Exception e) {
						logger.error("Exception deserializing node update " + e.toString());
						continue;
					}
					
					ClusterNodeImpl node = (ClusterNodeImpl)nodes.get(update.getNode());
					if(node == null) { 
						node = new ClusterNodeImpl();
						node.start(update);
						nodes.put(node.getId(), node);
					} else { 
						node.update(update);
					}
				}
				
			}
		}
	}

	
	
	

}
