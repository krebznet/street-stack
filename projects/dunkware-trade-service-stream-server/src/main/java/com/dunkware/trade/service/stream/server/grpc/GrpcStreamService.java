package com.dunkware.trade.service.stream.server.grpc;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dunkware.net.proto.stream.GAutoCompleteRequest;
import com.dunkware.net.proto.stream.GAutoCompleteResponse;
import com.dunkware.net.proto.stream.GStreamSpec;
import com.dunkware.net.proto.stream.GStreamSpecsRequest;
import com.dunkware.net.proto.stream.GStreamSpecsResponse;
import com.dunkware.net.proto.stream.service.GStreamServiceGrpc.GStreamServiceImplBase;
import com.dunkware.trade.service.stream.server.autosearch.AutoSearchService;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.StreamControllerService;
import com.dunkware.trade.service.stream.server.controller.util.GStreamSpecBuilder;
import com.dunkware.trade.service.stream.server.tick.StreamTickService;

import io.grpc.stub.StreamObserver;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;

@GrpcService(GStreamServiceImplBase.class)
@Service
@Profile("StreamController")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GrpcStreamService extends GStreamServiceImplBase {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${consumer.kafka.brokers}")
	private String consumerKafkaBrokers;

	@Autowired
	private StreamControllerService controllerService;

	@Autowired
	private StreamTickService tickService;
	
	
	@Autowired
	private AutoSearchService autoSearchService; 
	

	// Streaming AI --> Learn From You Events As They Happpen 
	
	@Override
	public void streamSpecs(GStreamSpecsRequest request, StreamObserver<GStreamSpecsResponse> responseObserver) {
		try {
			GStreamSpecsResponse.Builder respBuilder = GStreamSpecsResponse.newBuilder();
			List<GStreamSpec> gspecs = new ArrayList<GStreamSpec>();
			for (StreamController stream : controllerService.getStreams()) {
				gspecs.add(GStreamSpecBuilder.build(stream));
			}
			respBuilder.addAllSpecs(gspecs);
			responseObserver.onNext(respBuilder.build());
			responseObserver.onCompleted();	
		} catch (Exception e) {
			responseObserver.onError(e);
		}
	}


	@Override
	public StreamObserver<GAutoCompleteRequest> autoCompleteSearch(
			StreamObserver<GAutoCompleteResponse> responseObserver) {
		return new StreamObserver<GAutoCompleteRequest>() {

			@Override
			public void onNext(final GAutoCompleteRequest value) {
				try {
					String results = autoSearchService.response(value.getRequest());
					responseObserver.onNext(GAutoCompleteResponse.newBuilder().setResponse(results).build());

				} catch (Exception e) {
					logger.error("Exception invoking auto search engine " + e.toString());
					onError(e);
					// TODO: handle exception
				}
				


				
			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				
			}
			
			
	
	};
	}
	
	
	

	
	
	
	
	
	
	
	
	

	

	
	
	
	
	
	
	



}
