package com.dunkware.spring.test.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dunkware.street.model.ExchangeRef;
import com.dunkware.street.model.ExchangeRef.StatusEnum;
import com.dunkware.street.model.ExchangeSession;
import com.dunkware.street.model.TickerRef;
import com.dunkware.street.model.TickerSession;
import com.dunkware.street.model.TickerStat;
import com.dunkware.street.model.TickerVariable;
import com.dunkware.street.server.ApiApi;

@CrossOrigin("*")
public class MockExchange implements ApiApi {

	@Override
	public ResponseEntity<List<ExchangeSession>> apiExchangesExchangeIdSessionsGet(Integer exchangeId) {
		// TODO Auto-generated method stub
		return ApiApi.super.apiExchangesExchangeIdSessionsGet(exchangeId);
	}

	@Override
	public ResponseEntity<List<TickerRef>> apiExchangesExchangeIdTickersGet(Integer exchangeId) {
		// TODO Auto-generated method stub
		TickerRef ref = new TickerRef();
		ref.setBaseVersion(3.3);
		ref.setExchange(1);
		ref.setId(3);
		ref.setName("Apple Corporation");
		ref.setIdentifier("AAPL");
		List<TickerRef> lists = new ArrayList<TickerRef>();
		lists.add(ref);
		return ResponseEntity.ok(lists);
	
	}

	@Override
	public ResponseEntity<List<TickerSession>> apiExchangesExchangeIdTickersTickerIdSessionsGet(Integer exchangeId,
			Integer tickerId) {
		// TODO Auto-generated method stub
		return ApiApi.super.apiExchangesExchangeIdTickersTickerIdSessionsGet(exchangeId, tickerId);
	}

	@Override
	public ResponseEntity<List<TickerStat>> apiExchangesExchangeIdTickersTickerIdSessionsSessionIdStatsGet(
			Integer exchangeId, Integer tickerId, Integer sessionId) {
		// TODO Auto-generated method stub
		return ApiApi.super.apiExchangesExchangeIdTickersTickerIdSessionsSessionIdStatsGet(exchangeId, tickerId, sessionId);
	}

	@Override
	public ResponseEntity<TickerStat> apiExchangesExchangeIdTickersTickerIdStatsStatIdGet(Integer exchangeId,
			Integer tickerId, Integer statId) {
		// TODO Auto-generated method stub
		return ApiApi.super.apiExchangesExchangeIdTickersTickerIdStatsStatIdGet(exchangeId, tickerId, statId);
	}

	@Override
	public ResponseEntity<List<TickerVariable>> apiExchangesExchangeIdTickersTickerIdVariablesGet(Integer exchangeId,
			Integer tickerId) {
		
		// TODO Auto-generated method stub
		return ApiApi.super.apiExchangesExchangeIdTickersTickerIdVariablesGet(exchangeId, tickerId);
	}

	@CrossOrigin("*")
	@Override
	public ResponseEntity<List<ExchangeRef>> apiExchangesGet() {
		ExchangeRef ref = new ExchangeRef();
		ref.setId(1);
		ref.setIdentifier("us_equity");
		ref.setName("NYSE/NASDAQ");
		ref.setStatus(StatusEnum.PREMARKET);
		return ResponseEntity.ok(Arrays.asList(ref));

	}
	
	

}
