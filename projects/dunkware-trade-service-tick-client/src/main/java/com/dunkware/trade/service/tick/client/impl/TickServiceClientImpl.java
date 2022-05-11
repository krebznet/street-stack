package com.dunkware.trade.service.tick.client.impl;

import com.dunkware.common.util.helpers.DHttpHelper;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.tick.client.TickServiceClient;
import com.dunkware.trade.service.tick.client.TickServiceClientException;
import com.dunkware.trade.service.tick.client.TickServiceClientFeed;
import com.dunkware.trade.tick.model.consumer.TickConsumerSpec;
import com.dunkware.trade.tick.service.protocol.ticker.TSTickerListGetReq;
import com.dunkware.trade.tick.service.protocol.ticker.TickerListGetResp;
import com.dunkware.trade.tick.service.protocol.ticker.spec.TradeTickerListSpec;

public class TickServiceClientImpl implements TickServiceClient  {

	private String endpoint; 
	
	@Override
	public void connect(String endpoint) throws TickServiceClientException {
		if(endpoint.endsWith("/")) { 
			endpoint = endpoint.substring(0,endpoint.length() - 1);
		}
		this.endpoint = endpoint;
		
		try {
			ping();
		} catch (Exception e) {
			int count = 0; 
			while(true) { 
				try {
					if(count > 5) { 
						throw new TickServiceClientException("Can not ping server after 5 seconds");
					}
					Thread.sleep(1000);
					try {
						ping();
						return;
					} catch (Exception e2) {
						count++;
					}
				} catch (Exception e2) {
					throw new TickServiceClientException("Can not ping server after 5 seconds");
				}
			}
		}
	}
	


	@Override
	public String getEndpoint() {
		return endpoint;
	}

	@Override
	public TickServiceClientFeed createFeed(TickConsumerSpec spec) throws TickServiceClientException {
		TickServiceClientFeedImpl feed = new TickServiceClientFeedImpl();
		feed.start(spec, this);
		return feed;
		
	}


	@Override
	public Object postResponseObject(String path, Object request, Class responseClass) throws TickServiceClientException {
		String endpoint = this.endpoint + path;
		try {
			return DHttpHelper.postJsonWithResponse(endpoint, request, responseClass);
		} catch (Exception e) {
			throw new TickServiceClientException("Exception posting with json response " + e.toString(),e);
		}
		
	}
	

	@Override
	public void ping() throws Exception {
		try {
			String endpoint = this.endpoint + "/ticker/echo?name=hello";
			DHttpHelper.getURLContent(endpoint);
		} catch (Exception e) {
			throw new Exception("Echo request failed " + e.toString());
		}
	}



	@Override
	public void post(String path, Object request) throws TickServiceClientException {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		TSTickerListGetReq req = new TSTickerListGetReq();
		req.setName("Test500");
		try {
			System.out.println(DJson.serializePretty(req));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public TradeTickerListSpec getTickerList(String listId) throws TickServiceClientException {
		TSTickerListGetReq req = new TSTickerListGetReq();
		req.setName(listId);
		try {
			TickerListGetResp resp = (TickerListGetResp)postResponseObject("/ticker/list/get", req, TickerListGetResp.class);
			if(resp.getCode().equals("ERROR")) { 
				throw new TickServiceClientException("Get ticker list returned ERROR code " + resp.getError());
			}
			return resp.getList();
		} catch (Exception e) {
			throw new TickServiceClientException("Exception Getting Ticker List "+ e.toString());
		}
	}
	
	
	
	
	


	
	
	

	

	
}
