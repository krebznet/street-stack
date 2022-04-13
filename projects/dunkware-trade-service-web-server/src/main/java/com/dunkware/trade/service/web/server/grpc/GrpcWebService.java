package com.dunkware.trade.service.web.server.grpc;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dunkware.net.proto.chart.Grid;
import com.dunkware.net.proto.core.GList;
import com.dunkware.net.proto.stream.GAutoCompleteRequest;
import com.dunkware.net.proto.stream.GAutoCompleteResponse;
import com.dunkware.net.proto.stream.GEntitySignalQuery;
import com.dunkware.net.proto.web.GWebServiceGrpc.GWebServiceImplBase;
import com.dunkware.trade.service.web.server.autosearch.AutoSearchService;
import com.dunkware.trade.service.web.server.grid.SignalGridMockData1;
import com.dunkware.trade.service.web.server.grid.SignalGrids;
import com.dunkware.trade.service.web.server.grpc.proxy.GrpcAutoCompleteProxy;
import com.dunkware.trade.service.web.server.grpc.proxy.GrpcAutoCompleteProxyListener;

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
	

	@Autowired
	private ApplicationContext ac;
	
	@PostConstruct
	public void init() { 
		System.out.println("init grpc web service");
	}

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
	public StreamObserver<GAutoCompleteRequest> autoCompleteSearch(
			StreamObserver<GAutoCompleteResponse> responseObserver) {
		
		if(1 ==  1) { 
		
			
			return new StreamObserver<GAutoCompleteRequest>() {
				
				String json = null;

				@Override
				public void onNext(GAutoCompleteRequest value) {
					// on next we forward request to the proxy 
					responseObserver.onNext(GAutoCompleteResponse.newBuilder().setResponse(value.getRequest()).build());

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
			
	
		
		GrpcAutoCompleteProxy proxy = new GrpcAutoCompleteProxy();
		GrpcAutoCompleteProxyListener proxyListener;
		ac.getAutowireCapableBeanFactory().autowireBean(proxy);
		
		try {
			proxy.start();
		} catch (Exception e) {
			logger.error("Exception starting stream service auto complete proxy " + e.toString(), e);
			responseObserver.onError(e);
			return null;
			
		}
		
		class ProxyResponseListener implements GrpcAutoCompleteProxyListener {

			@Override
			public void onResponse(String response) {
				// this means we got a response 
				// back from proxy lets send GAutoCompleteResponse
				GAutoCompleteResponse gresp = GAutoCompleteResponse.newBuilder().setResponse(response).build();
				responseObserver.onNext(gresp);
			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onComplete() {
				// TODO Auto-generated method stub
				
			} 
			
			
		}
		proxyListener = new ProxyResponseListener();
		proxy.addListener(proxyListener);
		
		return new StreamObserver<GAutoCompleteRequest>() {
			
			String json = null;

			@Override
			public void onNext(GAutoCompleteRequest value) {
				// on next we forward request to the proxy 
				proxy.handleSearch(value.getRequest());

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
