package com.dunkware.trade.service.web.server.grpc.proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.net.proto.stream.GAutoCompleteRequest;
import com.dunkware.net.proto.stream.GAutoCompleteResponse;
import com.dunkware.net.proto.stream.service.GStreamServiceGrpc;
import com.dunkware.trade.service.web.server.grpc.GrpcWebServiceChannels;

import io.grpc.stub.StreamObserver;

// needs to be autowired manually
public class GrpcAutoCompleteProxy {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private List<GrpcAutoCompleteProxyListener> listeners = new ArrayList<GrpcAutoCompleteProxyListener>();
	private Semaphore listenerLock = new Semaphore(1);

	private GStreamServiceGrpc.GStreamServiceStub stub;

	@Autowired()
	private GrpcWebServiceChannels channels;
	
	private StreamObserver<GAutoCompleteRequest> request;

	public GrpcAutoCompleteProxy() {

	}

	public void start() throws Exception {

		
		StreamObserver<GAutoCompleteResponse> responseListener = new StreamObserver<GAutoCompleteResponse>() {

			@Override
			public void onNext(GAutoCompleteResponse value) {
				String proxyResponse = value.getResponse();
				notifyResponse(value.getResponse());
				
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
		try {
			stub = GStreamServiceGrpc.newStub(channels.getStreamServiceChannel());
			request = stub.autoCompleteSearch(responseListener);
			
		} catch (Exception e) {
			logger.error("Exception connecting to stream service grpc channel in auto complete proxy " + e.toString(),e);
		}
	}

	public void handleSearch(String searchString) {
			GAutoCompleteRequest req =  GAutoCompleteRequest.newBuilder().setRequest(searchString).build();
			request.onNext(req);
	}
	
	
	private void notifyResponse(String response) { 
		try {
			listenerLock.acquire();
			for (GrpcAutoCompleteProxyListener grpcAutoCompleteProxyListener : listeners) {
				grpcAutoCompleteProxyListener.onResponse(response);
			}
		} catch (Exception e) {
		} finally {
			listenerLock.release();
		}
	}

	public void addListener(GrpcAutoCompleteProxyListener listener) {
		try {
			listenerLock.acquire();
			listeners.add(listener);
		} catch (Exception e) {
		} finally {
			listenerLock.release();
		}
	}

	public void removeListener(GrpcAutoCompleteProxyListener listener) {
		try {
			listenerLock.acquire();
			listeners.remove(listener);
		} catch (Exception e) {

		} finally {
			listenerLock.release();
		}
	}

}
