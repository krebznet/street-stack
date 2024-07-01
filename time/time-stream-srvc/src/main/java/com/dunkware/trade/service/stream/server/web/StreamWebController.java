package com.dunkware.trade.service.stream.server.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.java.utils.glazed.grid.DataGridUpdate;
import com.dunkware.java.utils.glazed.grid.GlazedDataGrid;
import com.dunkware.spring.runtime.controller.UserException;
import com.dunkware.spring.runtime.services.ExecutorService;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeBean;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.StreamControllerService;
import com.dunkware.trade.service.stream.server.repository.StreamSessionRepo;
import com.dunkware.trade.service.stream.server.web.components.EntitySessionVarGrid;

import ca.odell.glazedlists.ObservableElementList;
import reactor.core.publisher.Flux;

@RestController
public class StreamWebController {

	@Autowired
	private StreamWebService webService; 
	
	@Autowired
	private StreamControllerService streamService; 
	
	@Autowired
	private ExecutorService executorService; 
	
	private StreamSessionRepo sessionRepo;
	
//	/stream/v1/web/stream/grid/session/nodes?stream=us_equity
	@GetMapping(path = "/stream/v1/web/stream/grid/session/nodes", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<List<DataGridUpdate>> streamSessionNodes(@RequestParam() String stream) throws Exception {
		ObservableElementList<StreamSessionNodeBean> nodes = webService.getStreamNodes(stream);
		GlazedDataGrid grid = GlazedDataGrid.newInstance(nodes, webService.getExecutor(), "getId");
		grid.start();
		return grid.getUpdates();
	}
	
	
	@GetMapping(path = "/stream/v1/web/stream/grid/signals", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<List<DataGridUpdate>> streamSessionSignals(@RequestParam() String stream) throws Exception {
		ObservableElementList<StreamSessionNodeBean> nodes = webService.getStreamNodes(stream);
		GlazedDataGrid grid = GlazedDataGrid.newInstance(nodes, webService.getExecutor(), "getId");
		grid.start();
		return grid.getUpdates();
	}
	
	
	
	@GetMapping(path = "/stream/v1/web/stream/session/entity/vars/", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public String entitySessionVars(@RequestParam String stream, @RequestParam int entity) throws Exception { 
		// okay make the return type easier or lets think for one second give me a secon. 
		return null;
		

	}
	
	

	@GetMapping(path = "/stream/v1/web/stream/sessions", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<List<DataGridUpdate>> streamSessions(@RequestParam String stream) throws Exception { 
		// okay make the return type easier or lets think for one second give me a secon. 
		try {
			StreamController controller = streamService.getStreamByName(stream);
			EntitySessionVarGrid grid = new EntitySessionVarGrid();
			//return grid.init(executorService.get(), controller, entity);
			
		} catch (Exception e) {
			// UserException will set bad http request - their problem not ours
			throw new UserException("Invalid Stream " + stream);
		}
		return null;
		

	}
	
	

}
