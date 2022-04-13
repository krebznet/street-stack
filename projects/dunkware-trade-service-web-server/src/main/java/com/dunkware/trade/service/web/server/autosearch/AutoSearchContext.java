package com.dunkware.trade.service.web.server.autosearch;

import com.dunkware.net.proto.stream.GAutoCompleteRequest;
import com.dunkware.net.proto.stream.GAutoCompleteResponse;
import com.dunkware.trade.service.web.server.autosearch.strategies.AutoSearchEchoStrategy;

import io.grpc.stub.StreamObserver;

public  class AutoSearchContext implements StreamObserver<GAutoCompleteRequest>{
	
	private StreamObserver<GAutoCompleteResponse> response; 
	private StreamObserver<GAutoCompleteRequest> request;
	
	private RequestController requestController;
	
	private AutoSearchStrategy searchStrategy = new AutoSearchEchoStrategy();
	
	
	/**
	 * Nice, so GRPC web service autoSearchMethod will instantiate this 
	 * it will have pluggable search strategies and the first one we hard 
	 * code is echo stratgy. 
	 * @param responseObserver
	 * @return
	 */
	public StreamObserver<GAutoCompleteRequest> init (StreamObserver<GAutoCompleteResponse> responseObserver) {
		
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
	public void onNext(GAutoCompleteRequest arg0) {
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
