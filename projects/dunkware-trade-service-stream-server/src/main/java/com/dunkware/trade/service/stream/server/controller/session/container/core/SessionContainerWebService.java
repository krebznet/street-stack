package com.dunkware.trade.service.stream.server.controller.session.container.core;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainer;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerService;
import com.dunkware.trade.service.stream.server.controller.session.container.scanner.SessionContainerEntityScanner;
import com.dunkware.trade.service.stream.server.controller.session.container.scanner.SessionContainerEntityScannerStream;
import com.dunkware.trade.service.stream.server.streaming.StreamingAdapter;
import com.dunkware.xstream.container.proto.EntityScannerStartReq;
import com.dunkware.xstream.container.proto.EntityScannerStartResp;
import com.dunkware.xstream.model.scanner.SessionEntityScanner;

@RestController
@CrossOrigin(origins = "*") 
public class SessionContainerWebService {

	
	@Autowired
	private SessionContainerService containerService; 
	
	private Map<String,SessionContainerEntityScanner> scanners = new ConcurrentHashMap<String,SessionContainerEntityScanner>();
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker streamInfo = MarkerFactory.getMarker("StreamInfo");
	private Marker entityScanner = MarkerFactory.getMarker("EntityScanner");
	
	@PostMapping(path = "/stream/session/entity/scanner/start")
	public EntityScannerStartResp entityScannerStart(@RequestBody() EntityScannerStartReq req) {
		try {
			logger.info(entityScanner,"Handling REST Entity Scanner Start Request " + DJson.serializePretty(req));	
		} catch (Exception e) {
			logger.error(entityScanner, "Shit can't event deserialize EntityScannerStartReq in start API call");
		}
		
		EntityScannerStartResp resp = new EntityScannerStartResp();
		SessionContainer container = null;
		try {
			container = containerService.getContainer(req.getScanner().getStreamIdentifier());
		} catch (Exception e) {
			logger.error(entityScanner, "Exception getting session container with identifier " + req.getScanner().getStreamIdentifier());
			logger.error(streamInfo, "Exception getting session container with identifier " + req.getScanner().getStreamIdentifier() + " " + e.toString());
			resp.setException("Stream " + req.getScanner().getStreamIdentifier() + " not found");
			resp.setSuccess(false);
			return resp;
		}
		
		String scannerId = null;
		try {
			SessionContainerEntityScanner entityScanner = new SessionContainerEntityScanner();
			logger.info(this.entityScanner, "Starting Entity Scanner");
			scannerId = entityScanner.start(req,container);
			logger.info(this.entityScanner, "Started Entity Scanner");
			this.scanners.put(scannerId, entityScanner);
			resp.setSuccess(true);
			resp.setScannerId(scannerId);
			return resp;
		} catch (Exception e) {
			logger.error(this.entityScanner, "Exception Starting Entity Scanner " + e.toString());
			logger.warn("Exception Starting Session Container Entity Scanner " + e.toString());
			resp.setSuccess(false);
			resp.setException("Exception Starting Scanner " + e.toString());
			return resp;
		}
		
	}
	
	
	@GetMapping(path = "/stream/session/entity/scanner/stop")
	public void entityScannerStop(@RequestParam() String scannerId) throws Exception {
		logger.info(streamInfo, "Stopping Scanner ID " + scannerId);
		SessionContainerEntityScanner scanner = scanners.get(scannerId);
		if(scanner != null) { 
			scanner.dispose();
			logger.info(streamInfo, "Stopped Scanner ID " + scannerId);
			scanners.remove(scannerId);
		}
		
		
	}
	
	@PostMapping(path = "/stream/session/entity/scanner/run")
	public ResponseEntity<StreamingResponseBody> entityScannerRun(@RequestBody() SessionEntityScanner scanner) throws Exception {
		SessionContainer container = null;
		try {
			container = containerService.getContainer(scanner.getStreamIdentifier());
		} catch (Exception e) {
			throw new Exception("Stream Session " + scanner.getStreamIdentifier() + " not found");
		}
		String scannerId = null;
		SessionContainerEntityScanner entityScanner = null;
		try {
			entityScanner = new SessionContainerEntityScanner();
			EntityScannerStartReq req = new EntityScannerStartReq();
			req.setScanner(scanner);
			req.setVars(new ArrayList<String>());
			
			scannerId = entityScanner.start(req,container);
			this.scanners.put(scannerId, entityScanner);
		} catch (Exception e) {
			throw new Exception("Exception starting scanner " + e.toString());
		}
		
		StreamingAdapter adapter = new StreamingAdapter(scannerId);
		SessionContainerEntityScannerStream stream = new SessionContainerEntityScannerStream();
		stream.start(entityScanner, adapter);
		  return ResponseEntity.ok()
			        .contentType(MediaType.APPLICATION_STREAM_JSON)
			        .body(adapter);

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
