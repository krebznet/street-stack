package com.dunkware.trade.net.data.server.stream.signals;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.common.util.datagrid.DataGridUpdate;
import com.dunkware.spring.runtime.controller.UserException;
import com.dunkware.trade.net.data.server.stream.signals.beangrids.StreamSignalGrid;
import com.dunkware.trade.net.data.server.stream.signals.beangrids.StreamSignalTypeStatsGrid;
import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;
import com.dunkware.trade.service.data.model.signals.query.StreamSignalQuery;
import com.dunkware.trade.service.data.model.signals.query.StreamSignalTypeStatsQuery;

import reactor.core.publisher.Flux;

@RestController
public class StreamSignalWebService {

	
	@Autowired
	private StreamSignalService signalService; 
	
	
	
	@PostMapping(path = "/data/v1/stream/signal/query/stats/grid")
	public Flux<List<DataGridUpdate>> sessionSignalTypeBeanSearch(@RequestBody() StreamSignalTypeStatsQuery query, @RequestParam String stream) throws Exception { 
		StreamSignalProvider signalProvider = signalService.getProvider(stream);
		if(signalProvider == null) { 
			throw new UserException("Stream " + stream + " not found");
		}
		StreamSignalTypeStatsGrid grid = signalProvider.signalTypeSatsGrid(query);
		grid.getDataGrid().start();
		return grid.getDataGrid().getUpdates();
	}
	
	
	
	@PostMapping(path = "/data/v1/stream/signal/query/grid")
	public Flux<List<DataGridUpdate>> sessionSignaleBeanSearch(@RequestBody() StreamSignalQuery query, @RequestParam String stream) throws Exception { 
		StreamSignalProvider signalProvider = signalService.getProvider(stream);
		if(signalProvider == null) { 
			throw new UserException("Stream " + stream + " not found");
		}
		StreamSignalGrid grid = signalProvider.signalGrid(query);
		grid.getDataGrid().start();
		return grid.getDataGrid().getUpdates();
	
	}
	
	
	@PostMapping(path = "/data/v1/stream/signal/query/data")
	public List<StreamSignalBean> signalSearch(@RequestBody() StreamSignalQuery query, @RequestParam() String stream) throws Exception { 
		StreamSignalProvider provider = signalService.getProvider(stream);
		if(provider == null) { 
			throw new UserException("Stream " + stream + " not found");
		}
		return provider.signalDataQuery(query);
	}
	
}
