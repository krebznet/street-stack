package com.dunkware.trade.service.stream.server.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.xstream.model.scanner.SessionEntityScanner;

@RestController
public class StreamResourceWebService {

	@Autowired
	private StreamResourceService resourceService; 
	
	@PostMapping(path = "/stream/resources/scanner/entity/save")
	public @ResponseBody() SessionEntityScanner saveScanner(@RequestBody() SessionEntityScanner scanner) throws Exception { 
		return resourceService.saveOrInsertScanner(scanner);
	
	}
	
	@GetMapping(path = "/stream/resources/scanner/entity/list")
	public @ResponseBody() List<SessionEntityScanner> scannerList(@RequestParam() String identifier) throws Exception { 
		return resourceService.getStreamEntityScanners(identifier);
	
	}
	
	
	
	@GetMapping(path = "/stream/resources/scanner/entity/delete")
	public @ResponseBody() void scannerDelete(@RequestParam() long id) throws Exception { 
		resourceService.deleteEntityScanner(id);
	}
}
