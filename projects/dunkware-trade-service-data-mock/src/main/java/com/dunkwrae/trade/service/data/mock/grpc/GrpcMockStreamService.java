package com.dunkwrae.trade.service.data.mock.grpc;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dunkware.net.proto.stream.GStreamSpec;
import com.dunkware.net.proto.stream.GStreamSpecsRequest;
import com.dunkware.net.proto.stream.GStreamSpecsResponse;
import com.dunkware.net.proto.stream.service.GStreamServiceGrpc.GStreamServiceImplBase;
import com.dunkwrae.trade.service.data.mock.stream.MockStream;
import com.dunkwrae.trade.service.data.mock.stream.MockStreamService;
import com.dunkwrae.trade.service.data.mock.util.MockMarker;
import com.dunkwrae.trade.service.data.mock.util.MockProtoUtil;

import io.grpc.stub.StreamObserver;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;


@GrpcService(GStreamServiceImplBase.class)
@Service
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GrpcMockStreamService extends GStreamServiceImplBase {

	@Autowired
	private MockStreamService streamService; 
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@PostConstruct
	private void testInit() { 
		logger.info(MockMarker.getMarker(), "Starting Stream Service GRPC");
	}
	
	@Override
	public void streamSpecs(GStreamSpecsRequest request, StreamObserver<GStreamSpecsResponse> responseObserver) {
		GStreamSpecsResponse.Builder respBuilder = GStreamSpecsResponse.newBuilder();
		for (MockStream stream : streamService.getStreams()) {
			GStreamSpec spec = MockProtoUtil.createStreamSpec(stream);
			respBuilder.addSpecs(spec);
		}
		responseObserver.onNext(respBuilder.build());
		responseObserver.onCompleted();
		
		
		
	}
	
	

}
