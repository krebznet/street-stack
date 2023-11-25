package com.dunkware.trade.net.data.server.stream.signals.beangrids;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.glazed.GlazedDataGrid;
import com.dunkware.common.util.glazed.GlazedDataGridListener;
import com.dunkware.trade.net.data.server.stream.signals.StreamSignalProvider;
import com.dunkware.trade.net.data.server.stream.signals.beanlists.StreamSignalList;

public class StreamSignalGrid implements GlazedDataGridListener {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamSignalTypeStatGrid");
	
	private GlazedDataGrid dataGrid;

	private StreamSignalList list; 
	private StreamSignalProvider provider; 
	
	public void start(StreamSignalList list, StreamSignalProvider provider) { 
		dataGrid = GlazedDataGrid.newInstance(list.getList(), provider.getExecutor(),"rowId");
		dataGrid.addListener(this);
		this.provider = provider;
		this.list = list;
	}
	
	public GlazedDataGrid getDataGrid() { 
		return dataGrid; 
	}
	
	@Override
	public void onGridDispose(GlazedDataGrid dataGrid) {
		if(logger.isDebugEnabled()) { 
			logger.debug(marker, "StreamSignalTypeStatsGrid onGridDispose disposing bean list");
		}
		list.dispose();
	}

	@Override
	public void onGridInit(GlazedDataGrid dataGrid) {

		
	} 
	
	
}
