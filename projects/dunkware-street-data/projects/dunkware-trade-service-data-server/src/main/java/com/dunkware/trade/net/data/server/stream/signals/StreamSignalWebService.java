package com.dunkware.trade.net.data.server.stream.signals;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.common.util.datagrid.DataGridUpdate;
import com.dunkware.common.util.glazed.GlazedDataGrid;
import com.dunkware.spring.runtime.controller.UserException;
import com.dunkware.trade.net.data.server.stream.signals.beangrids.StreamSignalGrid;
import com.dunkware.trade.net.data.server.stream.signals.beangrids.StreamSignalTypeStatsGrid;
import com.dunkware.trade.net.data.server.stream.signals.beanlists.StreamSignalList;
import com.dunkware.trade.net.data.server.stream.signals.beanlists.StreamSignalTypeStatsList;
import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;
import com.dunkware.trade.service.data.model.signals.query.StreamSignalQuery;
import com.dunkware.trade.service.data.model.signals.query.StreamSignalTypeStatsQuery;

import reactor.core.publisher.Flux;

@RestController
public class StreamSignalWebService {

	
	@Autowired
	private StreamSignalService signalService; 
	
	
	
	@PostMapping(path = "/data/v1/stream/signal/query/stats/grid", produces = MediaType.APPLICATION_NDJSON_VALUE)
	public Flux<List<DataGridUpdate>> sessionSignalTypeBeanSearch(@RequestBody() StreamSignalTypeStatsQuery query, @RequestParam String stream) throws Exception { 
		StreamSignalProvider signalProvider = signalService.getProvider(stream);
		if(signalProvider == null) { 
			throw new UserException("Stream " + stream + " not found");
		}
		StreamSignalTypeStatsList list = signalProvider.signalTypeStatsList(query);
		
		GlazedDataGrid dataGrid = GlazedDataGrid.newInstance(list.getList(), signalProvider.getExecutor(), "getRowId");
		dataGrid.addListener(list);
		dataGrid.start();
		return dataGrid.getUpdates();
		
	}
	
	
	
	@PostMapping(path = "/data/v1/stream/signal/query/grid", produces = MediaType.APPLICATION_NDJSON_VALUE)
	public Flux<List<DataGridUpdate>> sessionSignaleBeanSearch(@RequestBody() StreamSignalQuery query, @RequestParam String stream) throws Exception { 
		StreamSignalProvider signalProvider = signalService.getProvider(stream);
		if(signalProvider == null) { 
			throw new UserException("Stream " + stream + " not found");
		}
		StreamSignalList list = signalProvider.signalList(query);
		GlazedDataGrid dataGrid = GlazedDataGrid.newInstance(list.getList(), signalProvider.getExecutor(), "getRowId");
		dataGrid.addListener(list);
		dataGrid.start();
		return dataGrid.getUpdates();
	
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
