package com.dunkware.trade.service.stream.server.blueprint;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dunkware.common.util.datagrid.DataGridUpdate;
import com.dunkware.common.util.glazed.GlazedDataGrid;
import com.dunkware.spring.runtime.services.ExecutorService;
import com.dunkware.trade.service.stream.json.blueprint.WebSignalTypeDeleteResponse;
import com.dunkware.xstream.model.signal.type.StreamSignalType;

import reactor.core.publisher.Flux;

@RestController
public class StreamBlueprintWebService {
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

	@Autowired
	private StreamBlueprintService blueprintService; 
	
	//| curl -H "Content-Type: application/json" -H "Transfer-Encoding: chunked" -X GET -d @- 'http://localhost:8086/stream/v1/blueprint/dash/vars?stream=us_equity'
// curl -v -H  "http://localhost:8032/trade/v1/dash/core/brokers"
// curl -v -H  'http://testrock1.dunkware.net:32100/stream/v1/blueprint/dash/signals?stream=us_equity'
		
	
	
	@Autowired
	private ExecutorService executorService; 
	
	@GetMapping(path = "/stream/v1/blueprint/dash/signals", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<List<DataGridUpdate>> blueprintSignals(@RequestParam() String stream) {
		StreamBlueprint bp = null;
		try {
			bp = blueprintService.getBlueprint(stream);
			
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, "Stream blueprint not found for " + stream);
		}
		GlazedDataGrid grid = GlazedDataGrid.newInstance(bp.getSignalBeans(),executorService.get(),"getId");
		Flux<List<DataGridUpdate>> results = grid.getUpdates();
		grid.start();
		return results;
	
	}
	
	
	@GetMapping(path = "/stream/v1/blueprint/dash/vars", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<List<DataGridUpdate>> blueprintVars(@RequestParam() String stream) {
		StreamBlueprint bp = null;
		try {
			bp = blueprintService.getBlueprint(stream);
			
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, "Stream blueprint not found for " + stream);
		}
		GlazedDataGrid grid = GlazedDataGrid.newInstance(bp.getVarBeans(),executorService.get(),"getId");
		grid.start();
		return grid.getUpdates();
		
	
	}
	
	
	public @ResponseBody List<StreamBlueprintSignalBean> signalBeans(@RequestParam() String stream) { 
		return null;
	}

	
	@PostMapping( path = "/stream/v1/blueprint/signal/delete")
	public WebSignalTypeDeleteResponse deleteSignalType(@RequestParam() int signalId, @RequestParam String stream) { 
		if(1 == 1) { 
			WebSignalTypeDeleteResponse resp = new WebSignalTypeDeleteResponse();
			resp.setHasConflicts(true);
			resp.getConflicts().add("Please Get Duncan's Permission");
			return resp;
		}
		StreamBlueprint blueprint = null;
		try {
			blueprint = blueprintService.getBlueprint(stream);
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, "Stream Ident not found " + stream);
		}
		
		try {
		//	blueprint.addSignal(model);
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, e.toString());

		}
		return null;
	}
	
	
	@PostMapping( path = "/stream/v1/blueprint/signal/add")
	public void addSignalType(@RequestBody StreamSignalType model, @RequestParam String stream) { 
		StreamBlueprint blueprint = null;
		try {
			blueprint = blueprintService.getBlueprint(stream);
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, "Stream Ident not found " + stream);
		}
		
		try {
			blueprint.addSignal(model);
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, e.toString());

		}
	}
	
	
	@GetMapping("/stream/v1/blueprint/signals/get")
	public @ResponseBody() List<StreamBlueprintSignalBean> getSignals(@RequestParam() String stream) { 
		StreamBlueprint bp = null;
		try {
			bp = blueprintService.getBlueprint(stream);
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, "Stream blueprint not found for " + stream);
		}
		try {
			return bp.getSignalBeans();

		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, "Stream blueprint signal not found fo");
			
		}
	}
	
	
}
