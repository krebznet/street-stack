package com.dunkware.trade.service.tick.client.impl;

import java.util.List;

import com.dunkware.trade.service.tick.client.TickServiceClient;
import com.dunkware.trade.service.tick.client.TickServiceClientException;
import com.dunkware.trade.service.tick.client.TickServiceClientFeed;
import com.dunkware.trade.tick.model.consumer.TickConsumerSpec;
import com.dunkware.trade.tick.model.feed.TickFeedSubscriptionBean;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.trade.tick.service.protocol.ticker.TSTickerListGetReq;
import com.dunkware.trade.tick.service.protocol.ticker.TickerListGetResp;
import com.dunkware.trade.tick.service.protocol.ticker.spec.TradeTickerListSpec;
import com.dunkware.utils.core.http.DunkHttp;
import com.dunkware.utils.core.json.DunkJson;
import com.fasterxml.jackson.core.type.TypeReference;

public class TickServiceClientImpl implements TickServiceClient {

	private String endpoint;

	@Override
	public void connect(String endpoint) throws TickServiceClientException {
		if (endpoint.endsWith("/")) {
			endpoint = endpoint.substring(0, endpoint.length() - 1);
		}
		this.endpoint = endpoint;

		try {
			ping();
		} catch (Exception e) {
			int count = 0;
			while (true) {
				try {
					if (count > 5) {
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
	public Object postResponseObject(String path, Object request, Class responseClass)
			throws TickServiceClientException {
		String endpoint = this.endpoint + path;
		try {
			return DunkHttp.postBodyResponse(endpoint, request, responseClass);
		} catch (Exception e) {
			throw new TickServiceClientException("Exception posting with json response " + e.toString(), e);
		}

	}

	@Override
	public void ping() throws Exception {
		try {
			String endpoint = this.endpoint + "/ticker/echo?name=hello";
			DunkHttp.getURLContent(endpoint);
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
			System.out.println(DunkJson.serializePretty(req));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	@Override
	public List<TickFeedSubscriptionBean> getSubscriptions() throws TickServiceClientException {
		try {
			List<TickFeedSubscriptionBean> beans = null;
			String resp = DunkHttp.getURLContent(getEndpoint() + "/feed/subscriptions");
			beans = DunkJson.getObjectMapper().readValue(resp,new TypeReference<List<TickFeedSubscriptionBean>>(){});
	        return beans;
		} catch (Exception e) {
			throw new TickServiceClientException("Sorry no bueno " + e.toString());
		}
	}

	@Override
	public TradeTickerListSpec getTickerList(String listId) throws TickServiceClientException {
		TSTickerListGetReq req = new TSTickerListGetReq();
		req.setName(listId);
		try {
			TickerListGetResp resp = (TickerListGetResp) postResponseObject("/ticker/list/get", req,
					TickerListGetResp.class);
			if (resp.getCode().equals("ERROR")) {
				throw new TickServiceClientException("Get ticker list returned ERROR code " + resp.getError());
			}
			return resp.getList();
		} catch (Exception e) {
			throw new TickServiceClientException("Exception Getting Ticker List " + e.toString());
		}
	}

	@Override
	public TradeTickerSpec getTickerBySymbol(String symbol) throws TickServiceClientException {
		
		throw new TickServiceClientException("Fuck Me man implement the fucking thing");
	
	}
	
	

}
