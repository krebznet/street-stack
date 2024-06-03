package com.dunkware.street.server;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import com.dunkware.street.model.ExchangeRef;
import com.dunkware.street.model.ExchangeSession;
import com.dunkware.street.model.TickerRef;
import com.dunkware.street.model.TickerSession;
import com.dunkware.street.model.TickerStat;
import com.dunkware.street.model.TickerVariable;

public class Test implements ApiApi {

	@Override
	public Optional<NativeWebRequest> getRequest() {
		// TODO Auto-generated method stub
		return ApiApi.super.getRequest();
	}

	@Override
	public ResponseEntity<List<ExchangeSession>> _apiExchangesExchangeIdSessionsGet(Integer exchangeId) {
		// TODO Auto-generated method stub
		return ApiApi.super._apiExchangesExchangeIdSessionsGet(exchangeId);
	}

	@Override
	public ResponseEntity<List<ExchangeSession>> apiExchangesExchangeIdSessionsGet(Integer exchangeId) {
		// TODO Auto-generated method stub
		return ApiApi.super.apiExchangesExchangeIdSessionsGet(exchangeId);
	}

	@Override
	public ResponseEntity<List<TickerRef>> _apiExchangesExchangeIdTickersGet(Integer exchangeId) {
		// TODO Auto-generated method stub
		return ApiApi.super._apiExchangesExchangeIdTickersGet(exchangeId);
	}

	@Override
	public ResponseEntity<List<TickerRef>> apiExchangesExchangeIdTickersGet(Integer exchangeId) {
		// okay so we need to make a call to the thinger --
		
		// TODO Auto-generated method stub
		return ApiApi.super.apiExchangesExchangeIdTickersGet(exchangeId);
	}

	@Override
	public ResponseEntity<List<TickerSession>> _apiExchangesExchangeIdTickersTickerIdSessionsGet(Integer exchangeId,
			Integer tickerId) {
		// TODO Auto-generated method stub
		return ApiApi.super._apiExchangesExchangeIdTickersTickerIdSessionsGet(exchangeId, tickerId);
	}

	@Override
	public ResponseEntity<List<TickerSession>> apiExchangesExchangeIdTickersTickerIdSessionsGet(Integer exchangeId,
			Integer tickerId) {
		// TODO Auto-generated method stub
		return ApiApi.super.apiExchangesExchangeIdTickersTickerIdSessionsGet(exchangeId, tickerId);
	}

	@Override
	public ResponseEntity<List<TickerStat>> _apiExchangesExchangeIdTickersTickerIdSessionsSessionIdStatsGet(
			Integer exchangeId, Integer tickerId, Integer sessionId) {
		// TODO Auto-generated method stub
		return ApiApi.super._apiExchangesExchangeIdTickersTickerIdSessionsSessionIdStatsGet(exchangeId, tickerId, sessionId);
	}

	@Override
	public ResponseEntity<List<TickerStat>> apiExchangesExchangeIdTickersTickerIdSessionsSessionIdStatsGet(
			Integer exchangeId, Integer tickerId, Integer sessionId) {
		// TODO Auto-generated method stub
		return ApiApi.super.apiExchangesExchangeIdTickersTickerIdSessionsSessionIdStatsGet(exchangeId, tickerId, sessionId);
	}

	@Override
	public ResponseEntity<TickerStat> _apiExchangesExchangeIdTickersTickerIdStatsStatIdGet(Integer exchangeId,
			Integer tickerId, Integer statId) {
		// TODO Auto-generated method stub
		return ApiApi.super._apiExchangesExchangeIdTickersTickerIdStatsStatIdGet(exchangeId, tickerId, statId);
	}

	@Override
	public ResponseEntity<TickerStat> apiExchangesExchangeIdTickersTickerIdStatsStatIdGet(Integer exchangeId,
			Integer tickerId, Integer statId) {
		// TODO Auto-generated method stub
		return ApiApi.super.apiExchangesExchangeIdTickersTickerIdStatsStatIdGet(exchangeId, tickerId, statId);
	}

	@Override
	public ResponseEntity<List<TickerVariable>> _apiExchangesExchangeIdTickersTickerIdVariablesGet(Integer exchangeId,
			Integer tickerId) {
		// TODO Auto-generated method stub
		return ApiApi.super._apiExchangesExchangeIdTickersTickerIdVariablesGet(exchangeId, tickerId);
	}

	@Override
	public ResponseEntity<List<TickerVariable>> apiExchangesExchangeIdTickersTickerIdVariablesGet(Integer exchangeId,
			Integer tickerId) {
		// TODO Auto-generated method stub
		return ApiApi.super.apiExchangesExchangeIdTickersTickerIdVariablesGet(exchangeId, tickerId);
	}

	@Override
	public ResponseEntity<List<ExchangeRef>> _apiExchangesGet() {
		// TODO Auto-generated method stub
		return ApiApi.super._apiExchangesGet();
	}

	@Override
	public ResponseEntity<List<ExchangeRef>> apiExchangesGet() {
		// TODO Auto-generated method stub
		return ApiApi.super.apiExchangesGet();
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	
}
