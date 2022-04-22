package com.dunkware.net.cluster.server.core;

import com.dunkware.net.proto.cluster.GNodeStats;
import com.dunkware.net.proto.cluster.service.GClusterServiceGrpc.GClusterServiceImplBase;

import io.grpc.stub.StreamObserver;

public class GrpcClusterService extends GClusterServiceImplBase {

	@Override
	public StreamObserver<GNodeStats> nodeStats(StreamObserver<GNodeStats> responseObserver) {
		// TODO Auto-generated method stub
		return super.nodeStats(responseObserver);
	}

	
	

	
}
