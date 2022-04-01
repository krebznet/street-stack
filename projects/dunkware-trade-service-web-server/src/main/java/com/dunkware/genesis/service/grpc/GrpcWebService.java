package com.dunkware.genesis.service.grpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dunkware.genesis.service.grid.SignalGridMockData1;
import com.dunkware.genesis.service.grid.SignalGrids;
import com.dunkware.net.proto.chart.Grid;
import com.dunkware.net.proto.core.GList;
import com.dunkware.net.proto.stream.GEntitySignalQuery;
import com.dunkware.net.proto.web.GWebServiceGrpc.GWebServiceImplBase;

import io.grpc.stub.StreamObserver;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;


@GrpcService(GWebServiceImplBase.class)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Service
public class GrpcWebService extends GWebServiceImplBase  {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${service.mock}")
	private boolean mock; 

	
	// this will have connector to DataService
	// this will have connector to stream service 
	// this will have connector to beach service 
	
	@Override
	public void testSignalGrid(GEntitySignalQuery request, StreamObserver<Grid> responseObserver) {
		if(mock) { 
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
	
	





}
