package com.dunkware.net.cluster.server.grpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dunkware.net.cluster.server.core.ClusterService;
import com.dunkware.net.cluster.server.publishers.GrpcNodeUpdateStream;
import com.dunkware.net.proto.cluster.GNodeUpdateRequest;
import com.dunkware.net.proto.cluster.GNodeUpdateResponse;
import com.dunkware.net.proto.cluster.service.GClustererviceGrpc.GClustererviceImplBase;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService()
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

}
