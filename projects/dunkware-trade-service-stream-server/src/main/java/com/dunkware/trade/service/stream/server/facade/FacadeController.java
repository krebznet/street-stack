package com.dunkware.trade.service.stream.server.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.stream.server.controller.StreamControllerService;
import com.dunkware.trade.service.stream.server.facade.api.ApiApi;
import com.dunkware.trade.service.stream.server.facade.model.ExchangeRef;
import com.dunkware.trade.service.stream.server.facade.model.TickerRef;

import jakarta.annotation.PostConstruct;


@RestController
public class FacadeController implements ApiApi  {
	
	@Autowired
	private StreamControllerService streamController; 

	@PostConstruct
	private void testLoad() { 
		System.out.println("loading");
	}
	
	@Override
	public ResponseEntity<List<TickerRef>> apiExchangesExchangeIdTickersGet(Integer exchangeId) {
		// TODO Auto-generated method stub
		return ApiApi.super.apiExchangesExchangeIdTickersGet(exchangeId);
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
	
	

}
