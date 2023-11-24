package com.dunkware.trade.net.data.server.stream.signals.sessionn.query;

import com.dunkware.common.util.glazed.GlazedDataGrid;
import com.dunkware.common.util.glazed.GlazedDataGridListener;
import com.dunkware.trade.net.data.server.stream.signals.StreamSignals;
import com.dunkware.trade.service.data.model.signals.query.StreamSignalTypeSessionQuery;

/**
 * OKay strams the stram signals session type search 
 * as streaming data.
 * @author duncankrebs
 *
 */
public class SessionSignalTypeQueryGrid implements GlazedDataGridListener {

	
	private GlazedDataGrid dataGrid;
	
	
	public void start(StreamSignalTypeSessionQuery query, StreamSignals signals) { 
		// okay think here now.... 
		
	}
	@Override
	public void onGridDispose(GlazedDataGrid dataGrid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGridInit(GlazedDataGrid dataGrid) {
		// TODO Auto-generated method stub
		
	}
	
	public GlazedDataGrid getDataGrid() { 
		return dataGrid;
	}
}
