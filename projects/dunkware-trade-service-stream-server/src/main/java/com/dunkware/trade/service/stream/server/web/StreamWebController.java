package com.dunkware.trade.service.stream.server.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.common.util.datagrid.DataGridUpdate;
import com.dunkware.common.util.glazed.GlazedDataGrid;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeBean;

import ca.odell.glazedlists.ObservableElementList;
import reactor.core.publisher.Flux;

@RestController
public class StreamWebController {

	@Autowired
	private StreamWebService webService; 
	
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
	
	
	
	@GetMapping(path = "/stream/v1/web/stream/session/entity/vars/grid", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<DataGridUpdate> entitySessionVars(@RequestParam String stream, @RequestParam int entityId) { 
		// VarSnapshot - ID - IDENT - value -
		//webService.getEntitySessionVarGrid(entityId);
		return null;
	}
	
	
	

}
