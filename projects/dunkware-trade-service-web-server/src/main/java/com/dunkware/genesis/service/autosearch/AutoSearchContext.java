package com.dunkware.genesis.service.autosearch;

import com.dunkware.genesis.service.autosearch.strategies.AutoSearchEchoStrategy;
import com.dunkware.net.proto.stream.GAutCompleteRequest;
import com.dunkware.net.proto.stream.GAutoCompleteResponse;

import io.grpc.stub.StreamObserver;

public  class AutoSearchContext implements StreamObserver<GAutCompleteRequest>{
	
	private StreamObserver<GAutoCompleteResponse> response; 
	private StreamObserver<GAutCompleteRequest> request;
	
	private RequestController requestController;
	
	private AutoSearchStrategy searchStrategy = new AutoSearchEchoStrategy();
	
	
	/**
	 * Nice, so GRPC web service autoSearchMethod will instantiate this 
	 * it will have pluggable search strategies and the first one we hard 
	 * code is echo stratgy. 
	 * @param responseObserver
	 * @return
	 */
	public StreamObserver<GAutCompleteRequest> init (StreamObserver<GAutoCompleteResponse> responseObserver) {
		
		this.response = responseObserver;
		this.searchStrategy.init(this);
		return this;
	}
	
	@Override
	public void onCompleted() {
		//on completed dispose strategy so it can release its resources 
		searchStrategy.dispose();
	}



	@Override
	public void onError(Throwable arg0) {
		// TODO handle this? 
		
	}



	@Override
	public void onNext(GAutCompleteRequest arg0) {
		// handle a search request
		//String searchResponse = searchStrategy.handleSearch(arg0.getRequest());
		try {
		//	GAutoCompleteResponse resp = GAutoCompleteResponse.newBuilder().setResponse(searchResponse).build();
			//this.response.onNext(resp);	
			
		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}
	

	private static class RequestController extends Thread  { 
		
		public void run() { 
			
		}
	}
	
	
	private static class ResponsePublisher extends Thread { 
		
	}
	

}
