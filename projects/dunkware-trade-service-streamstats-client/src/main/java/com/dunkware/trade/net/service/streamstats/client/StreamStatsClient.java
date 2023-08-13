package com.dunkware.trade.net.service.streamstats.client;

import java.util.concurrent.Future;

import com.dunkware.trade.net.service.streamstats.client.impl.StreamStatsClientBean;
import com.dunkware.trade.net.service.streamstats.client.proto.StreamStatsRequest;
import com.dunkware.trade.net.service.streamstats.client.proto.StreamStatsResponse;

public interface StreamStatsClient {
	
	void load(StreamStatsClientBean bean) throws StreamStatsClientException; 
	
	Future<StreamStatsResponse> request(StreamStatsRequest req) throws StreamStatsClientException; 
	
	void dispose();

}
