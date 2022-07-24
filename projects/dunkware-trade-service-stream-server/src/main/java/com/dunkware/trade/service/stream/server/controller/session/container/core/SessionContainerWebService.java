package com.dunkware.trade.service.stream.server.controller.session.container.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainer;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerService;
import com.dunkware.trade.service.stream.server.controller.session.container.scanner.SessionContainerEntityScanner;
import com.dunkware.trade.service.stream.server.controller.session.container.scanner.SessionContainerEntityScannerStream;
import com.dunkware.trade.service.stream.server.streaming.StreamingAdapter;
import com.dunkware.xstream.container.proto.EntityScannerStartReq;
import com.dunkware.xstream.container.proto.EntityScannerStartResp;

@RestController
public class SessionContainerWebService {

	
	@Autowired
	private SessionContainerService containerService; 
	
	private Map<String,SessionContainerEntityScanner> scanners = new ConcurrentHashMap<String,SessionContainerEntityScanner>();
	
	
	@PostMapping(path = "/stream/session/entity/scanner/start")
	public EntityScannerStartResp entityScannerStart(@RequestBody() EntityScannerStartReq req) { 
		EntityScannerStartResp resp = new EntityScannerStartResp();
		SessionContainer container = null;
		try {
			container = containerService.getContainer(req.getScanner().getStreamIdentifier());
		} catch (Exception e) {
			resp.setException("Stream " + req.getScanner().getStreamIdentifier() + " not found");
			resp.setSuccess(false);
			return resp;
		}
		
		String scannerId = null;
		try {
			SessionContainerEntityScanner entityScanner = new SessionContainerEntityScanner();
			scannerId = entityScanner.start(req,container);
			this.scanners.put(scannerId, entityScanner);
			resp.setSuccess(true);
			resp.setScannerId(scannerId);
			return resp;
		} catch (Exception e) {
			resp.setSuccess(false);
			resp.setException("Exception Starting Scanner " + e.toString());
			return resp;
		}
		
	}
	
	
	@GetMapping(path = "/stream/session/entity/scanner/stop")
	public void entityScannerStop(@RequestParam() String scannerId) throws Exception { 
		SessionContainerEntityScanner scanner = scanners.get(scannerId);
		if(scanner != null) { 
			scanner.dispose();
			scanners.remove(scannerId);
		}
		
	}
	
	@GetMapping(path = "/stream/session/entity/scanner/stream")
	public ResponseEntity<StreamingResponseBody> entityScannerStream(@RequestParam() String scannerId) throws Exception {
		
		SessionContainerEntityScanner scanner = scanners.get(scannerId);
		if(scanner == null) { 
			throw new Exception("Scanner " + scannerId + " not found");
		}
		
		StreamingAdapter adapter = new StreamingAdapter(scannerId);
		SessionContainerEntityScannerStream stream = new SessionContainerEntityScannerStream();
		stream.start(scanner, adapter);
		  return ResponseEntity.ok()
			        .contentType(MediaType.APPLICATION_STREAM_JSON)
			        .body(adapter);
		//  return adapter; 
		
		
	}
	
	
}
