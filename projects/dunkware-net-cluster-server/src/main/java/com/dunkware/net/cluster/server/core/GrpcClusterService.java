package com.dunkware.net.cluster.server.core;

import com.dunkware.net.proto.cluster.GNodePing;
import com.dunkware.net.proto.cluster.service.GClusterServiceGrpc.GClusterServiceImplBase;

import io.grpc.stub.StreamObserver;

public class GrpcClusterService extends GClusterServiceImplBase {

	/**
	 * So this gets called once 
	 */
	@Override
	public StreamObserver<GNodePing> nodePing(StreamObserver<GNodePing> responseObserver) {
		// TODO Auto-generated method stub
		return super.nodePing(responseObserver);
		
		
	}

	
}
