package com.dunkware.trade.net.data.server.stream.signals.sessionn.query;

import com.dunkware.common.util.glazed.GlazedDataGrid;
import com.dunkware.common.util.glazed.GlazedDataGridListener;

/**
 * OKay strams the stram signals session type search 
 * as streaming data.
 * @author duncankrebs
 *
 */
public class SessionSignalTypeQueryGrid implements GlazedDataGridListener {

	
	private GlazedDataGrid dataGrid;
	

	public void start() { 
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
