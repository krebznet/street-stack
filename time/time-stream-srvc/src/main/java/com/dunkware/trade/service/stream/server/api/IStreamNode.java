package com.dunkware.trade.service.stream.server.api;

import java.util.List;

import reactor.core.publisher.Flux;

public interface IStreamNode {
	
	Flux<List<Object>> entityVariableStream(String ident) throws Exception;
	
	//ObservableElementList<Obserablebe>

}
