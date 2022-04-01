package com.dunkware.trade.service.data.service.grpc;

import com.dunkware.net.proto.data.service.GDataServiceGrpc.GDataServiceImplBase;
import com.dunkware.net.proto.stream.GEntitySignalQuery;
import com.dunkware.net.proto.stream.GEntitySignalSearchRequest;
import com.dunkware.net.proto.stream.GEntitySignalSearchResponse;

import io.grpc.stub.StreamObserver;



/**
 * This is the start of our GRPC Service API that will accept 
 * GEntitySignalSearchRequest that contains a GEntitySignalQuery
 * GEntityS
 * @author duncankrebs
 *
 */
public class GrpcMongoSearchService extends GDataServiceImplBase {

	@Override
	public void signalSearch(GEntitySignalSearchRequest request,
			StreamObserver<GEntitySignalSearchResponse> responseObserver) {
			GEntitySignalQuery query = request.getQuery();
			
			// List<Documents> as a search results
			// Duncan will do the work of converting your mongo signal search results which I think would be a list of Doucments
			// into the data format that this search method returns. 
			
			//TODO: sd -- help document
			
		super.signalSearch(request, responseObserver);
	}
	
	

	
	

	

}
