package com.dunkware.trade.service.stream.server.controller.session.container;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.trade.service.stream.server.controller.session.container.connection.SessionContainerConnection;
import com.dunkware.trade.service.stream.server.controller.session.container.connector.KafkaStreamConnector;
import com.dunkware.trade.service.stream.server.streaming.StreamingAdapter;
import com.dunkware.xstream.model.proto.SessionEntityScannerStartReq;
import com.dunkware.xstream.model.proto.SessionEntityScannerStartResp;
import com.dunkware.xstream.model.proto.SessionEntityScannerStopRequest;
import com.dunkware.xstream.model.proto.SessionEntityScannerUpdateReq;
import com.dunkware.xstream.model.proto.SessionEntityScannerUpdateResp;
import com.dunkware.xstream.net.service.KafkaStreamClientRequest;
import com.dunkware.xstream.net.service.KafkaStreamClientResponse;

@RestController
public class SessionContainerWebService {

	@Autowired
	private SessionContainerService containerService; 
	
	@Autowired
	private Cluster cluster;

	@Value("${kafka.brokers}")
	private String kafkaBrokers;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	private Map<String,SessionContainerEntityScanner> sessionScanners = new ConcurrentHashMap<String,SessionContainerEntityScanner>();
	
	
	@PostMapping(path = "/stream/container/client/request")
	public @ResponseBody KafkaStreamClientResponse kafkaStreamClientRequest(@RequestBody() KafkaStreamClientRequest request) { 
		KafkaStreamClientResponse resp = new KafkaStreamClientResponse();
		SessionContainer controller = containerService.getStreamContainer(request.getStream());
		if(controller == null) { 
			resp.setError(true);
			resp.setException("Stream container " + request.getStream() + " not found");
			return resp;
		}
		// now we want to -- define the topics
		String serverTopic = "stream_container_connection_" + request.getClientIdentifier() + "_server_" + DUUID.randomUUID(5);
		String clientTopic = "stream_container_connection_" + request.getClientIdentifier() + "_client_" + DUUID.randomUUID(5);
		try {
			KafkaStreamConnector connector = new KafkaStreamConnector(request.getClientIdentifier(), kafkaBrokers, serverTopic, clientTopic);
			
			SessionContainerConnection connection = new SessionContainerConnection();
			connection.start(connector, controller);
			controller.getConnections().add(connection);
			// so now that we have done this; 
			resp.setKafkaBrokers(kafkaBrokers);
			resp.setServerMessageTopic(serverTopic);
			resp.setClientMessageTopic(clientTopic);
			resp.setError(false);
			return resp;
		} catch (Exception e) {
			logger.error("Exception creating kafka stream connector " + e.toString());
			resp.setError(true);
			resp.setException("Internal exception creating kafka connector " + e.toString());
			return resp;
		}

		
		
	}
	
	// container/entity/search 
	
	/**
	 * Okay here you go, small incremental changes on this branch. 
	 * Not the big fuck changes. 
	 * @param request
	 * @return
	 */
	@PostMapping(path = "/stream/container/scanner/entity/start")
	public @ResponseBody() SessionEntityScannerStartResp entityScannerStart(@RequestBody() SessionEntityScannerStartReq req) { 
		SessionContainer container = containerService.getStreamContainer(req.getStreamIdentifier());
		if(container == null) { 
			// this means invalid stream identifier 
			SessionEntityScannerStartResp resp = new SessionEntityScannerStartResp();
			resp.setSuccess(false);
			resp.setError("Stream Identifier " + req.getStreamIdentifier() + " not found");
			return resp;
		}
		SessionContainerEntityScanner scanner = new SessionContainerEntityScanner();
		String scannerIdentifier = null;
		try {
			StreamingAdapter streamingAdapter = new StreamingAdapter();
			scannerIdentifier = "EntityScanner:" + DUUID.randomUUID(5);
			scanner.start(streamingAdapter,req.getScanner(),container,scannerIdentifier);
		} catch (Exception e) {
			SessionEntityScannerStartResp resp = new SessionEntityScannerStartResp();
			resp.setSuccess(false);
			resp.setError("Internal Exception " + e.toString());
			return resp;	
		}
		sessionScanners.put(scannerIdentifier,scanner);
		SessionEntityScannerStartResp resp = new SessionEntityScannerStartResp();
		resp.setSuccess(true);
		resp.setScannerId(scannerIdentifier);
		return resp;
		
		
		
		
	}
	
	
	@PostMapping(path = "/stream/container/scanner/entity/update")
	public @ResponseBody() SessionEntityScannerUpdateResp entityScannerUpdate(@RequestBody() SessionEntityScannerUpdateReq req) { 
		
		return null;
		
	}
	
	@GetMapping(path = "/stream/container/scanner/entity/data")
	public ResponseEntity<StreamingResponseBody> scannerData(@RequestParam() String scannerId) throws Exception {
		StreamingAdapter adapter = sessionScanners.get(scannerId).getStreamingAdapter();
		return ResponseEntity.ok()
		        .contentType(MediaType.APPLICATION_STREAM_JSON)
		        .body(adapter);
	}
	
	@PostMapping(path = "/stream/container/scanner/entity/stop")
	public void entityScannerStop(@RequestBody() SessionEntityScannerStopRequest req) { 
		return;
		
	}
}
