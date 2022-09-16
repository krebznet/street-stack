package com.dunkware.trade.service.web.server.stream;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.dunkware.common.util.grid.GridOptions;
import com.dunkware.common.util.grid.GridOptionsBuilder;
import com.dunkware.common.util.grid.format.GridFormat;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.spring.streaming.StreamingAdapter;
import com.dunkware.xstream.container.proto.EntityScannerStartReq;
import com.dunkware.xstream.container.proto.EntityScannerStartResp;
import com.dunkware.xstream.model.scanner.SessionEntityScanner;

@RestController
public class MockScannerController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private Map<String, MockEntityScanner> scanners = new ConcurrentHashMap<String, MockEntityScanner>();

	@PostMapping(path = "/mock/stream/session/entity/scanner/start")
	public EntityScannerStartResp entityScannerStart(@RequestBody() SessionEntityScanner scanner) {
		EntityScannerStartReq req = new EntityScannerStartReq();
		try {
			// Let's work together team
			// scanner.setStreamIdentifier(search.getFilterSearch().get) req.setVars(new
			req.setScanner(scanner);
			req.setVars(new ArrayList<String>());

		} catch (Exception e) {
			logger.error("Shit can't event deserialize EntityScannerStartReq in start API call");
		}

		EntityScannerStartResp resp = new EntityScannerStartResp();
		// mock grid column
		GridOptions options = GridOptionsBuilder.newInstnace().column("Entity", "Entity", GridFormat.TEXT)
				.column("Last", "Last", GridFormat.CURRENCY).column("Volume", "Volume", GridFormat.INTEGER).build();
		resp.setOptions(options);

		String scannerId = DUUID.randomUUID(4);
		MockEntityScanner mockScanner = new MockEntityScanner();
		mockScanner.start(scannerId);
		scanners.put(scannerId, mockScanner);
		resp.setScannerId(scannerId);
		resp.setSuccess(true);
		return resp;

	}

	
	@GetMapping(path = "/mock/stream/session/entity/scanner/stream")
	public ResponseEntity<StreamingResponseBody> entityScannerStream(@RequestParam() String scannerId)
			throws Exception {

		StreamingAdapter adapter = new StreamingAdapter(scannerId);
		try {
			MockEntityScanner scanner = scanners.get(scannerId);
			if (scanner == null) {
				throw new Exception("Scanner " + scannerId + " not found");
			}
			MockEntityScannerAdapter scannerAdapter = new MockEntityScannerAdapter();
			scannerAdapter.start(scanner, adapter);
		} catch (Exception e) {
			logger.error(e.toString());
			throw e;
		}

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(adapter);
		// return adapter;

	}

	@GetMapping(path = "/mock/stream/session/entity/scanner/stop")
	public void stopScanner(@RequestParam() String scannerId) throws Exception {

		try {
			MockEntityScanner scanner = scanners.get(scannerId);
			if (scanner == null) {
				throw new Exception("Scanner " + scannerId + " not found");
			}
			scanner.stop();
			scanners.remove(scanner);
			logger.info("Removed/stopped mock scanner " + scannerId);

		} catch (Exception e) {
			logger.error(e.toString());
			throw e;
		}

	}

}
