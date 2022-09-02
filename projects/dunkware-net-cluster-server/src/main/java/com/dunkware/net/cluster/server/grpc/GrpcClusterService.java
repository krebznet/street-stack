package com.dunkware.net.cluster.server.grpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.net.cluster.server.core.ClusterNode;
import com.dunkware.net.cluster.server.core.ClusterService;
import com.dunkware.net.cluster.server.core.ClusterWorkerNodeHold;
import com.dunkware.net.cluster.server.publishers.GrpcNodeUpdateStream;
import com.dunkware.net.proto.cluster.GNodeUpdateRequest;
import com.dunkware.net.proto.cluster.GNodeUpdateResponse;
import com.dunkware.net.proto.cluster.GReleaseWorkerNodesRequest;
import com.dunkware.net.proto.cluster.GReleaseWorkerNodesResponse;
import com.dunkware.net.proto.cluster.GReserveWorkerNodesRequest;
import com.dunkware.net.proto.cluster.GReserveWorkerNodesResponse;
import com.dunkware.net.proto.cluster.GReserveWorkerNodesResponse.GrantedNode;
import com.dunkware.net.proto.cluster.service.GClustererviceGrpc.GClustererviceImplBase;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService()
public class GrpcClusterService extends GClustererviceImplBase {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ClusterService cluster;

	@Override
	public void nodeUpdateStream(GNodeUpdateRequest request, StreamObserver<GNodeUpdateResponse> responseObserver) {
		try {
			new GrpcNodeUpdateStream(cluster, request, responseObserver);
		} catch (Exception e) {
			logger.error("Exception Building GrpcNodeStatusUpdater " + e.toString());
			responseObserver.onError(e);
			;
		}
	}

	@Override
	public void reserveWorkerNodes(GReserveWorkerNodesRequest request,
			StreamObserver<GReserveWorkerNodesResponse> responseObserver) {
		ClusterWorkerNodeHold hodld = cluster.getNodeService().workerNodeHoldRequest(request.getIdentifier(), request.getRequestedNodes());
		GReserveWorkerNodesResponse.Builder builder = GReserveWorkerNodesResponse.newBuilder();
		for (ClusterNode node : hodld.getNodes()) {
			builder.addGrantedNodes(GrantedNode.newBuilder().setNodeId(node.getId()).build());
		}
		responseObserver.onNext(builder.build());
		responseObserver.onCompleted();
	}

	@Override
	public void releaseWorkerNodes(GReleaseWorkerNodesRequest request,
			StreamObserver<GReleaseWorkerNodesResponse> responseObserver) {
		// TODO Auto-generated method stub
		cluster.getNodeService().releaseWorkerNodes(request.getIdentifier());
		responseObserver.onNext(GReleaseWorkerNodesResponse.newBuilder().build());
		responseObserver.onCompleted();
	}
	
	
	
	


	
	

}
