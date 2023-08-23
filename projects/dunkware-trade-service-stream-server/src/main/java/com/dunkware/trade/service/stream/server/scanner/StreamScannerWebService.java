package com.dunkware.trade.service.stream.server.scanner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.common.util.datagrid.DataGridUpdate;
import com.dunkware.trade.service.stream.json.query.WebStreamQuery;
import com.dunkware.trade.service.stream.server.controller.StreamControllerService;

import reactor.core.publisher.Flux;

@RestController
public class StreamScannerWebService {
	
	@Autowired()
	private StreamControllerService streamService; 
	
	
	@GetMapping(path = "/stream/v1/entity/scanner", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<List<DataGridUpdate>> entityScanner(@RequestBody() WebStreamQuery query, @RequestParam() String stream) throws Exception { 
		return null; 
		//StreamEntityScanner scanner = new StreamEntityScanner();
		// that will have a subsriber. 
	}
	
	// stream scanner 
	
	// update scanner 
	

}
