package com.dunkware.trade.service.stream.server.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.stream.server.controller.StreetExchange;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.StreamControllerService;
import com.dunkware.trade.service.stream.server.facade.api.ApiApi;
import com.dunkware.trade.service.stream.server.facade.model.ExchangeRef;
import com.dunkware.trade.service.stream.server.facade.model.ExchangeRef.StatusEnum;
import com.dunkware.trade.service.stream.server.facade.model.TickerRef;

import jakarta.annotation.PostConstruct;


@RestController
public class FacadeController implements ApiApi  {
	
	@Autowired
	private StreamControllerService streamControllerService;

	@PostConstruct
	private void testLoad() { 
		System.out.println("loading");
	}
	
	@Override
	public ResponseEntity<List<TickerRef>> apiExchangesExchangeIdTickersGet(Integer exchangeId) {

			try {
				StreetExchange exchange = streamControllerService.getStreamById((long)exchangeId);
				return ResponseEntity.ok().body(exchange.getExchangeTickers());
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}
		
	}
	
	

	@Override
	public ResponseEntity<List<TickerRef>> _apiExchangesExchangeIdTickersGet(Integer exchangeId) {
		// TODO Auto-generated method stub
		return ApiApi.super._apiExchangesExchangeIdTickersGet(exchangeId);
	}

	@Override
	public ResponseEntity<List<ExchangeRef>> _apiExchangesGet() {
		// TODO Auto-generated method stub
		return ApiApi.super._apiExchangesGet();
	}

	@Override
	public ResponseEntity<List<ExchangeRef>> apiExchangesGet() {
		
		List<ExchangeRef> results = new ArrayList<ExchangeRef>();
		for (StreamController	stream : streamControllerService.getStreams()) {
			ExchangeRef ref = new ExchangeRef();
			ref.setId((int)stream.getEntity().getId());
			ref.setName(stream.getName());
			ref.setIdentifier(stream.getName());
			if(stream.inSession()) { 
				ref.setStatus(StatusEnum.OPEN);
			} else { 
				ref.setStatus(StatusEnum.CLOSED);
			}
			results.add(ref);
		}
		
		return ResponseEntity.ok(results);
		
	
	}
	
	

}
