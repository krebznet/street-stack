package com.dunkware.trade.service.web.server.grpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dunkware.net.proto.chart.Grid;
import com.dunkware.net.proto.core.GList;
import com.dunkware.net.proto.stream.GAutCompleteRequest;
import com.dunkware.net.proto.stream.GAutoCompleteResponse;
import com.dunkware.net.proto.stream.GEntitySignalQuery;
import com.dunkware.net.proto.web.GWebServiceGrpc.GWebServiceImplBase;
import com.dunkware.trade.service.web.server.autosearch.AutoSearchService;
import com.dunkware.trade.service.web.server.grid.SignalGridMockData1;
import com.dunkware.trade.service.web.server.grid.SignalGrids;

import io.grpc.stub.StreamObserver;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;

@GrpcService(GWebServiceImplBase.class)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Service
public class GrpcWebService extends GWebServiceImplBase {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${service.mock}")
	private boolean mock;

	@Autowired
	private AutoSearchService autoSearch;

	// this will have connector to DataService
	// this will have connector to stream service
	// this will have connector to beach service

	@Override
	public void testSignalGrid(GEntitySignalQuery request, StreamObserver<Grid> responseObserver) {
		if (mock) {
			GList list = SignalGridMockData1.getList();
			try {
				Grid grid = SignalGrids.grid1(list);
				responseObserver.onNext(grid);
				responseObserver.onCompleted();

			} catch (Exception e) {
				logger.error("Exception returning mock signal gird " + e.toString());
				responseObserver.onError(e);

			}
			return;

		}

	}

	@Override
	public StreamObserver<GAutCompleteRequest> autoCompleteSearch(
			StreamObserver<GAutoCompleteResponse> responseObserver) {
		
		
		return new StreamObserver<GAutCompleteRequest>() {

			@Override
			public void onNext(GAutCompleteRequest value) {
				responseObserver.onNext(GAutoCompleteResponse.newBuilder().setResponse(value.getRequest()).build());
				responseObserver.onCompleted();
				// nned a proxy to another service 
				// in streem that will have strategy
				// will have to post vson. 
				
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
