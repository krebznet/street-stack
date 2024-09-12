package com.dunkware.trade.service.stream.serverd.web;

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
import com.dunkware.trade.service.stream.serverd.controller.StreamController;
import com.dunkware.trade.service.stream.serverd.controller.StreamControllerService;
import com.dunkware.trade.service.stream.serverd.repository.StreamSessionRepo;
import com.dunkware.trade.service.stream.serverd.web.components.EntitySessionVarGrid;

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
	

	//TODO: AVINASHANV-41 Web Service using data grid stream 
	/**
	 * so look what we do. for each type of data stream we have an observable element list 
	 * on some service that we use to stream for data grid population, could be the nodes
	 * in the stream session, the orders for a trade or trades all the model objects
	 * can be observable and putting them in ObservableElementList is a great way to bind
	 * to ui and send these Data Grid updates. To create a new web service for streaming 
	 * a data grid of model objects its a 5 minute task, create the web service use GlazedDataGrid
	 * go into react create new component that defines the grid columns and calls your DshBoardDaaGrid
	 * component with an end point that invokes a service like this and consumes the Data grid updates
	 * then your logic looks at the update type and calls the relevent api on data grid, this way we don't
	 * refresh entire grids of data, but we just update specific rows and that is the story. 
	 * @param stream
	 * @return
	 * @throws Exception
	 */
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
